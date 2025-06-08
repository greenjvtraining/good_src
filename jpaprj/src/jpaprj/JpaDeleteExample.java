package jpaprj;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaDeleteExample {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaprj");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        try {
            // ----- Delete (삭제) -----

            // 6.1. 삭제할 엔티티를 조회하여 영속 상태로 만듦
            Integer memoIdToDelete = 3; // 삭제할 ID (예: 1번)
            MyMemo memoToDelete = em.find(MyMemo.class, memoIdToDelete);

            if (memoToDelete == null) {
                System.out.println("삭제할 메모를 찾을 수 없습니다.");
                em.getTransaction().rollback();
                return;
            }

            System.out.println("--- 엔티티 조회 후 ---");
            System.out.println("삭제할 memo: " + memoToDelete.getMemo());
            System.out.println("memoToDelete 상태: 영속");

            // 6.2. remove() 호출: 영속성 컨텍스트에서 엔티티 제거 및 삭제 예약
            em.remove(memoToDelete);

            System.out.println("--- remove() 호출 후 ---");
            System.out.println("memoToDelete 상태: 삭제 (영속성 컨텍스트에서 제거됨, DB 삭제 예약)");
            // 이 시점에는 아직 DB에 DELETE 쿼리가 날아가지 않음

            // 6.3. 트랜잭션 커밋
            // 커밋 시점에 영속성 컨텍스트는 변경사항을 플러시
            // 쓰기 지연 SQL 저장소에 있던 DELETE SQL이 DB로 전송
            em.getTransaction().commit();

            System.out.println("--- commit() 호출 후 ---");
            System.out.println("memoToDelete 상태: 삭제 (DB에서도 삭제 완료)");

            // 6.4. 삭제된 엔티티를 다시 조회 시도 (영속성 컨텍스트와 DB 모두에서 없음)
            MyMemo deletedMemo = em.find(MyMemo.class, memoIdToDelete);
            System.out.println("삭제 후 다시 조회: " + deletedMemo); // null 출력 예상

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
