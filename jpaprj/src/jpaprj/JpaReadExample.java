package jpaprj;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaReadExample {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaprj");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		try {
			// --- Read (조회) ---
			
			//조회할 ID (예시: 1번 ID)
			Integer memoIdToFind = 2;
			
			System.out.println("--- find() 호출 전 ---");
            System.out.println("조회 대상 ID: " + memoIdToFind);

            // 4.1. 1차 캐시에서 먼저 조회 시도
            // 영속성 컨텍스트의 1차 캐시에 해당 ID의 엔티티가 있는지 확인
            MyMemo foundMemo1 = em.find(MyMemo.class, memoIdToFind);
            
            System.out.println("--- find() 호출 1회 후 ---");
            if (foundMemo1 != null) {
                System.out.println("foundMemo1 상태: 영속");
                System.out.println("foundMemo1: " + foundMemo1.getMemo());
            } else {
                System.out.println("foundMemo1: null (DB에 해당 ID 없음)");
            }

            // 4.2. 1차 캐시에 없으면 데이터베이스에서 조회
            // DB에서 가져온 후, 영속성 컨텍스트의 1차 캐시에 저장하고 영속 상태로 만듦.
            // (만약 이미 1차 캐시에 있으면 DB 접근 없이 1차 캐시의 객체를 반환)
            MyMemo foundMemo2 = em.find(MyMemo.class, memoIdToFind);
            
            System.out.println("--- find() 호출 2회 후 (동일 ID) ---");
            if (foundMemo2 != null) {
                System.out.println("foundMemo2 상태: 영속");
                System.out.println("foundMemo2: " + foundMemo2.getMemo());
            }

            // 두 객체는 동일한 영속성 컨텍스트에 의해 관리되므로, 동일 인스턴스
            System.out.println("foundMemo1 == foundMemo2: " + (foundMemo1 == foundMemo2)); // true 출력 예상

            em.getTransaction().commit();
		}catch(Exception e) {
			if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
			e.printStackTrace();
		}finally {
			em.close();
			emf.close();
		}
	}

}
