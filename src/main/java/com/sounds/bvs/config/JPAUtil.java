package com.sounds.bvs.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = buildEntityManagerFactory();

	private JPAUtil() {

	}

	public static EntityManagerFactory buildEntityManagerFactory() {
		if (ENTITY_MANAGER_FACTORY == null) {
			ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("ACQ_MANAGER");
		}
		return ENTITY_MANAGER_FACTORY;
	}

	public static EntityManager getEnityManager() {
		return ENTITY_MANAGER_FACTORY.createEntityManager();
	}

	public static void closeEntityManagerFactory() {
		if (ENTITY_MANAGER_FACTORY.isOpen())
			ENTITY_MANAGER_FACTORY.close();
	}

}
