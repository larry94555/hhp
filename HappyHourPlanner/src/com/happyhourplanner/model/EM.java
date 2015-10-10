package com.happyhourplanner.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;



public class EM {
	
	private static EntityManager emInstance = EMF.get().createEntityManager();
	
	private EM() {}
	
	public static EntityManager get() {
		synchronized(emInstance) {
			return emInstance;
		}
	}
	
	public static void commit() {
		synchronized(emInstance) {
			emInstance.close();
			emInstance = EMF.get().createEntityManager();
		}
		
	}
	

}
