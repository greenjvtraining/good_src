package com.green.jpa_em.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.green.jpa_em.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

@Service
public class UserService {

	private EntityManagerFactory emf;
	private EntityManager em;
	
	public UserService() {
        // EntityManagerFactory 생성 (애플리케이션 시작 시 한 번만)
        emf = Persistence.createEntityManagerFactory("myPU");
        em = emf.createEntityManager();
    }
	
	// CREATE - 새로운 사용자 저장
    public void createUser(String username, String email, Integer age) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            User user = new User(username, email, age);
            em.persist(user); // 엔티티를 영속 상태로 만듦
            
            tx.commit();
            System.out.println("사용자 생성 완료: " + user);
        } catch (Exception e) {
            tx.rollback();
            System.err.println("사용자 생성 실패: " + e.getMessage());
        }
    }
    
    // READ - 사용자 조회 (Primary Key로)
    public User findUserById(Long id) {
        try {
            User user = em.find(User.class, id);
            
            if (user != null) {
                System.out.println("사용자 조회 성공: " + user);
            } else {
                System.out.println("ID " + id + "인 사용자를 찾을 수 없습니다.");
            }
            return user;
        } catch (Exception e) {
            System.err.println("사용자 조회 실패: " + e.getMessage());
            return null;
        }
    }
    
    // READ - 모든 사용자 조회 (JPQL 사용)
    public List<User> findAllUsers() {
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
            List<User> users = query.getResultList();
            System.out.println("전체 사용자 수: " + users.size());
            return users;
        } catch (Exception e) {
            System.err.println("전체 사용자 조회 실패: " + e.getMessage());
            return null;
        }
    }
    
    // READ - 특정 조건으로 사용자 조회
    public List<User> findUsersByAge(Integer minAge) {
        try {
            TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.age >= :minAge", User.class);
            query.setParameter("minAge", minAge);
            
            List<User> users = query.getResultList();
            System.out.println(minAge + "세 이상 사용자 수: " + users.size());
            return users;
        } catch (Exception e) {
            System.err.println("조건부 사용자 조회 실패: " + e.getMessage());
            return null;
        }
    }
    
    // UPDATE - 사용자 정보 수정
    public void updateUser(Long id, String newEmail) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            User user = em.find(User.class, id);
            if (user != null) {
                user.setEmail(newEmail); // 변경 감지(Dirty Checking)
                // em.merge() 호출 불필요 - 영속 상태의 엔티티는 자동으로 변경 감지됨
                
                tx.commit();
                System.out.println("사용자 정보 수정 완료: " + user);
            } else {
                tx.rollback();
                System.out.println("수정할 사용자를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            tx.rollback();
            System.err.println("사용자 정보 수정 실패: " + e.getMessage());
        }
    }
    
    // UPDATE - 준영속 엔티티 병합
    public void mergeUser(User detachedUser) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            User managedUser = em.merge(detachedUser);
            
            tx.commit();
            System.out.println("사용자 병합 완료: " + managedUser);
        } catch (Exception e) {
            tx.rollback();
            System.err.println("사용자 병합 실패: " + e.getMessage());
        }
    }
    
    // DELETE - 사용자 삭제
    public void deleteUser(Long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
                tx.commit();
                System.out.println("사용자 삭제 완료: " + user);
            } else {
                tx.rollback();
                System.out.println("삭제할 사용자를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            tx.rollback();
            System.err.println("사용자 삭제 실패: " + e.getMessage());
        }
    }
    
    // 페이징 처리 예제
    public List<User> findUsersWithPaging(int page, int size) {
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u ORDER BY u.id", User.class);
            query.setFirstResult(page * size);
            query.setMaxResults(size);
            
            List<User> users = query.getResultList();
            System.out.println("페이지 " + page + "의 사용자 " + users.size() + "명 조회");
            return users;
        } catch (Exception e) {
            System.err.println("페이징 조회 실패: " + e.getMessage());
            return null;
        }
    }
    
    // Named Query 사용 예제 (User 엔티티에 @NamedQuery 추가 필요)
    public User findUserByUsername(String username) {
        try {
            TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("사용자명 '" + username + "'인 사용자를 찾을 수 없습니다.");
            return null;
        } catch (Exception e) {
            System.err.println("사용자명으로 조회 실패: " + e.getMessage());
            return null;
        }
    }
    
    // EntityManager 상태 관리 예제
    public void demonstrateEntityStates() {
        // 1. 비영속 상태 (Transient)
        User newUser = new User("john", "john@example.com", 25);
        System.out.println("1. 비영속 상태: " + em.contains(newUser)); // false
        
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        // 2. 영속 상태 (Managed)
        em.persist(newUser);
        System.out.println("2. 영속 상태: " + em.contains(newUser)); // true
        
        // 3. 준영속 상태 (Detached)
        em.detach(newUser);
        System.out.println("3. 준영속 상태: " + em.contains(newUser)); // false
        
        // 4. 다시 영속 상태로
        User managedUser = em.merge(newUser);
        System.out.println("4. 다시 영속 상태: " + em.contains(managedUser)); // true
        
        tx.commit();
        
        // 5. 영속성 컨텍스트 종료 후 준영속 상태
        em.clear(); // 또는 em.close()
        System.out.println("5. 영속성 컨텍스트 종료 후: " + em.contains(managedUser)); // false
    }
    
    // 리소스 정리
    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
