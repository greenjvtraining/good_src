package jpaprj;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class JpaCratedQueryExample {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaprj");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            // 테스트를 위해 임시 데이터 삽입 (이미 DB에 있다면 생략 가능)
            if (em.find(MyMemo.class, 1) == null) {
                MyMemo memo1 = new MyMemo();
                memo1.setMemo("첫 번째 메모입니다.");
                em.persist(memo1);

                MyMemo memo2 = new MyMemo();
                memo2.setMemo("두 번째 중요한 메모입니다.");
                em.persist(memo2);

                MyMemo memo3 = new MyMemo();
                memo3.setMemo("세 번째 임시 메모.");
                em.persist(memo3);
                em.flush(); // commit 전에 DB에 반영 (IDENTITY 전략 아니면 필요 없을 수도 있음)
            }
            em.clear(); // 1차 캐시 비우기 (쿼리 시 DB에서 가져오는지 확인 위함)

            System.out.println("--- 1. 모든 MyMemo 엔티티 조회 ---");
            // JPQL: FROM 엔티티명
            TypedQuery<MyMemo> queryAll = em.createQuery("SELECT m FROM MyMemo m", MyMemo.class);
            List<MyMemo> allMemos = queryAll.getResultList();
            for (MyMemo memo : allMemos) {
                System.out.println("ID: " + memo.getMno() + ", Memo: " + memo.getMemo());
                // 조회된 엔티티들은 영속성 컨텍스트에 의해 관리됨 (영속 상태)
            }
            System.out.println("----------------------------------\n");


            System.out.println("--- 2. 특정 조건으로 MyMemo 엔티티 조회 (WHERE 절) ---");
            // JPQL: WHERE 절에 조건 추가
            // :paramName 형태로 파라미터 바인딩
            TypedQuery<MyMemo> queryByContent = em.createQuery(
                "SELECT m FROM MyMemo m WHERE m.memo LIKE :contentPattern", MyMemo.class);
            queryByContent.setParameter("contentPattern", "%메모%"); // %는 SQL의 LIKE와 동일
            List<MyMemo> filteredMemos = queryByContent.getResultList();
            for (MyMemo memo : filteredMemos) {
                System.out.println("ID: " + memo.getMno() + ", Memo: " + memo.getMemo());
            }
            System.out.println("----------------------------------\n");


            System.out.println("--- 3. 단일 결과 엔티티 조회 (getSingleResult()) ---");
            // 결과가 하나만 있을 것으로 예상될 때
            try {
                TypedQuery<MyMemo> querySingle = em.createQuery(
                    "SELECT m FROM MyMemo m WHERE m.mno = :mno", MyMemo.class);
                querySingle.setParameter("mno", 2); // 예시: ID가 2인 메모
                MyMemo singleMemo = querySingle.getSingleResult();
                System.out.println("Single Memo ID: " + singleMemo.getMno() + ", Memo: " + singleMemo.getMemo());
            } catch (javax.persistence.NoResultException e) {
                System.out.println("해당 조건의 결과가 없습니다.");
            } catch (javax.persistence.NonUniqueResultException e) {
                System.out.println("해당 조건의 결과가 두 개 이상입니다.");
            }
            System.out.println("----------------------------------\n");


            System.out.println("--- 4. 특정 필드(값)만 조회 (엔티티가 아닌 DTO 형태로 받는 경우) ---");
            // JPQL: SELECT 절에 특정 필드만 지정
            // 이때 반환 타입은 Object[] 또는 DTO 클래스여야 함 (생성자 호출 JPQL)
            TypedQuery<String> queryMemoContent = em.createQuery(
                "SELECT m.memo FROM MyMemo m WHERE m.mno = 1", String.class);
            String memoContent = queryMemoContent.getSingleResult();
            System.out.println("Memo Content for ID 1: " + memoContent);
            System.out.println("----------------------------------\n");


            em.getTransaction().commit(); // 모든 쿼리 실행 후 트랜잭션 커밋

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
	}

}
