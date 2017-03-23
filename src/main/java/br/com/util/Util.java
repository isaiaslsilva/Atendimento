package br.com.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum Util {
	
INSTANCE;
	
	private EntityManagerFactory factory;

	private Util(){
		factory = Persistence.createEntityManagerFactory("PUNIT");
	}
	
	public EntityManager getEntityManager(){
		return factory.createEntityManager();
	}

}
