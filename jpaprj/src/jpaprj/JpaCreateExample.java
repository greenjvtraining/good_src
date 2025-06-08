package jpaprj;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaCreateExample {
	public static void main(String[] args) {
		//1. EntityManagerFactory 생성 (애플리케이션당 하나만 생성)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaprj");
		
		//2. EntityManager 생성(스레드당 하나, 또는 요청당 하나)
		EntityManager em = emf.createEntityManager();
		
		try {
			//3. 트랜잭션 시작
			em.getTransaction().begin();
			
			//-- Create --(생성)
			//3.1. 비영속(Transient) 상태 : 새로운 MyMemo 객체 생성
			MyMemo newMemo = new MyMemo();
			newMemo.setMemo("새로운 메모입니다.");
			
			System.out.println("--- persist() 호출 전 ---");
			System.out.println("newMemo 상태 : 비영속"); // 영속성 컨텍스트 관리 범위 밖
			System.out.println("newMemo ID : " + newMemo.getMno()); // ID는 아직 null
			
			//3.2. 영속(Managed) 상태 : persist() 호출
			// persist()는 즉시 DB에 저장하는 것이 아니라, 영속성 컨텍스트에 저장
			// 이때 엔티티는 영속성 컨텍스트의 관리를 받게 됨(1차 캐시에 저장)
			em.persist(newMemo);
			
			System.out.println("--- persist() 호출 후 ---");
			System.out.println("newMemo 상태 : 영속 (아직 DB에 저장되지 않음, 영속성 컨텍스트에만");
			System.out.println("newMemo ID : " + newMemo.getMno()); // IDENTITY 전략의 경우, persist() 시 ID가 할당될 수 있음 (DB에 insert 발생)
			// SEQUENCE/TABLE 전략의 경우, commit 시 ID가 할당됨
			
			// 3.3. 트랜잭션 커밋: 영속성 컨텍스트의 변경 사항을 데이터베이스에 동기화 (쓰기 지연)
			// 이때 실제로 INSERT SQL이 데이터베이스로 전송됨
			em.getTransaction().commit();
			
			System.out.println("--- commit() 호출 후 ---");
			System.out.println("newMemo 상태: 영속 (DB에 저장 완료)");
			System.out.println("newMemo ID: " + newMemo.getMno()); // DB에서 할당된 최종 ID
			
		}catch(Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}finally {
			em.close();
			emf.close();
		}
		
		
		
	}
}
