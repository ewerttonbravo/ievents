package br.com.ievents.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory entityManagerFactory;
	
	private static EntityManager entityManager;
	
	static {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("ieventsPU");
		} catch (Throwable t) {
			throw new ExceptionInInitializerError(t);
		}
	}
	
	public static EntityManager getEntityManager() {
		if (null == entityManager) {
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.setFlushMode(FlushModeType.COMMIT);
		}
		return entityManager;
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
}
