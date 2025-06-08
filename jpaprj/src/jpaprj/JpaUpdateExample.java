package jpaprj;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUpdateExample {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaprj");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            // ----- Update (수정) -----

            // 5.1. 수정할 엔티티를 조회하여 영속 상태로 만듦
            // (1차 캐시에 있으면 가져오고, 없으면 DB에서 가져와서 1차 캐시에 저장)
            Integer memoIdToUpdate = 2; // 미리 DB에 1번 ID의 데이터가 있다고 가정
            MyMemo memoToUpdate = em.find(MyMemo.class, memoIdToUpdate);

            if (memoToUpdate == null) {
                System.out.println("수정할 메모를 찾을 수 없습니다.");
                em.getTransaction().rollback();
                return;
            }

            System.out.println("--- 엔티티 조회 후 ---");
            System.out.println("현재 memo: " + memoToUpdate.getMemo());
            System.out.println("memoToUpdate 상태: 영속");

            // 5.2. 영속 상태의 엔티티 데이터 변경
            // setter를 통해 필드 값을 변경합니다.
            // JPA는 트랜잭션 커밋 시점에 스냅샷(조회 시점의 상태)과 현재 엔티티의 상태를 비교하여 변경 감지
            String oldMemoContent = memoToUpdate.getMemo();
            memoToUpdate.setMemo("수정된 메모 내용입니다. - " + LocalDateTime.now());

            System.out.println("--- 엔티티 데이터 변경 후 ---");
            System.out.println("수정된 memo: " + memoToUpdate.getMemo());
            // 이때는 아직 DB에 UPDATE 쿼리가 날아가지 않음

            // 5.3. 트랜잭션 커밋
            // 커밋 시점에 영속성 컨텍스트는 변경 감지(Dirty Checking)를 수행하고,
            // 변경된 내용이 있으면 UPDATE SQL을 생성하여 DB에 전송.
            em.getTransaction().commit();

            System.out.println("--- commit() 호출 후 ---");
            System.out.println("memoToUpdate 상태: 영속 (DB에 수정 완료)");
            System.out.println("DB에 업데이트된 내용: " + memoToUpdate.getMemo()); // 변경된 내용이 DB에 반영됨

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
