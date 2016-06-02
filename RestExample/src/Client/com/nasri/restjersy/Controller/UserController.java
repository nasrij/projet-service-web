package Client.com.nasri.restjersy.Controller;

import java.io.Console;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.nasri.restjersey.EM;

import Client.com.nasri.restjersy.model.Club;
import Client.com.nasri.restjersy.model.Evenement;
import Client.com.nasri.restjersy.model.User;
import Server.com.nasri.restjersey.model.Point;

public class UserController {
	
 
    public User[] getAllUsers() {
        EntityManager em = EM.getEntityManager();
        try {
            javax.persistence.Query q = em.createQuery("select c from User as c");
            User[] u = (User[]) q.getResultList().toArray(new User[0]);
           
            int x=0;
            x++;
            	
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
    
    public User getUser(int id) {
        EntityManager em = EM.getEntityManager();
        try {
            User user = em.find(User.class, id);           	
            return user;
        } catch(Exception ex)
        {
            em.close();
        }
        finally {
        	 em.close();
        }
        return null;
    }
    

    public boolean addUser(User user) {
           EntityManager em = EM.getEntityManager();
           try {
           	  em.getTransaction().begin();
               em.persist(user);           
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
    
    public boolean deleteUser(User u) {
		EntityManager em = EM.getEntityManager();
		em.getTransaction().begin();		
		em.remove(u);
		em.getTransaction().commit();
		return true;
	}
    
    public boolean updateUser(User uu) {
		EntityManager em = EM.getEntityManager();
		em.getTransaction().begin();
		em.merge(uu);
		em.getTransaction().commit();
		return true;
	}
    public Club getUserClub(int id)
    {
    	ClubController ec = new  ClubController();
    	User user = getUser(id);
    	Club e = ec.getClub(user.getIdClub());
    	return e;
    	
    }
    
   

}
