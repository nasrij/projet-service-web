package Server.com.nasri.restjersey.controller;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.nasri.restjersey.EM;

import Client.com.nasri.restjersy.model.User;
import Server.com.nasri.restjersey.model.Badge;

public class BadgeController {
	
	 public Badge[] getAllBadges() {
	        EntityManager em = EM.getEntityManager();
	        try {
	            javax.persistence.Query q = em.createQuery("select c from Badge as c");
	            Badge[] u = (Badge[]) q.getResultList().toArray(new Badge[0]);
	           
	          
	            return u;
	        } catch(Exception ex)
	        {
	            em.close();
	        }
	        finally {
	        	 em.close();
	        }
	        return null;
	    }
	 public Badge getBadge(int id) {
	        EntityManager em = EM.getEntityManager();
	        try {
	            Badge badge = (Badge) em.find(Badge.class, id);           	
	            return badge;
	        } catch(Exception ex)
	        {
	            em.close();
	        }
	        finally {
	        	 em.close();
	        }
	        return null;
	    }
	 public boolean addBadge(Badge badge) {
	        EntityManager em = EM.getEntityManager();
	        try {
	        	  em.getTransaction().begin();
	            em.persist(badge);           
	        	  em.getTransaction().commit();

	            return true;
	        } catch(Exception ex)
	        {
	            em.close();
	        }
	        finally {
	        	 em.close();
	        }
	        return false;
	    }
	 
	 public boolean deleteBadge(int id) {
	        EntityManager em = EM.getEntityManager();	       	        	 
	        	  Badge bb=(Badge) em.find(Badge.class, id);
	              em.getTransaction().begin();
	        	  em.remove(bb);
	              em.getTransaction().commit();
	              em.close();

	        	  return true;	        	       
	    }

	 public boolean updateBadge(Badge b) {
	        EntityManager em = EM.getEntityManager();
	       
	        	  //Badge bb=(Badge) em.find(Badge.class, id);
	              em.getTransaction().begin();
	        	  em.merge(b);
	              em.getTransaction().commit();
	              em.close();

	        	  return true;	        	       
	    }

}
