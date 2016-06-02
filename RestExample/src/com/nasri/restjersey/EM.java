package com.nasri.restjersey;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EM {
	
	public static EntityManagerFactory emf;
	 
    public static EntityManager getEntityManager() {
        if (emf == null) {        	
            emf = Persistence.createEntityManagerFactory("myPU");        	
        }
        return emf.createEntityManager();
    }

}
