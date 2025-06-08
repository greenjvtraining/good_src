package jpaprj;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainJpaprj {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaprj");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        MyMemo entity = new MyMemo();
        entity.setMemo("Test memo1");
        em.persist(entity);
        
        em.getTransaction().commit();
        
        em.close();
        emf.close();
	}

}
