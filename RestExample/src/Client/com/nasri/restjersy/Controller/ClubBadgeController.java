package Client.com.nasri.restjersy.Controller;

import javax.persistence.EntityManager;

import com.nasri.restjersey.EM;

import Client.com.nasri.restjersy.model.Clubbadge;
import Client.com.nasri.restjersy.model.User;
import Server.com.nasri.restjersey.controller.BadgeController;
import Server.com.nasri.restjersey.model.Badge;

public class ClubBadgeController {
	
	public Badge[] getClubBadges(int id) {
        EntityManager em = EM.getEntityManager();
        try {
            javax.persistence.Query q = em.createQuery("select c from Clubbadge as c  where  c.idClub = :id");
			q.setParameter("id", id);
            Clubbadge[] u = (Clubbadge[]) q.getResultList().toArray(new Clubbadge[0]);     
            Badge []  badges = new Badge[u.length] ;
            int i=0;
            BadgeController cc = new BadgeController();
            for (Clubbadge clubbadge : u) {
            	badges[i] =  cc.getBadge(clubbadge.getIdClub());	
            	i++;
			}
            return badges;
        } catch(Exception ex)
        {
            em.close();
        }
        finally {
        	 em.close();
        }
        return null;
    }
	
	 public boolean addClubBadge( Clubbadge cb) {
         EntityManager em = EM.getEntityManager();
         
         try {
         	  em.getTransaction().begin();
             em.persist(cb);           
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
  
  public boolean deleteClubBadge(int id) {
		EntityManager em = EM.getEntityManager();
		Clubbadge bb=(Clubbadge) em.find(Clubbadge.class, id);
		em.getTransaction().begin();		
		em.remove(bb);
		em.getTransaction().commit();
		return true;
	}
  
  public Clubbadge getClubBadge(int id) {
      EntityManager em = EM.getEntityManager();
      try {
    	  Clubbadge cb = em.find(Clubbadge.class, id);           	
          return cb;
      } catch(Exception ex)
      {
          em.close();
      }
      finally {
      	 em.close();
      }
      return null;
  }
  

}
