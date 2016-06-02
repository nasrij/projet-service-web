package Client.com.nasri.restjersy.Controller;

import javax.persistence.EntityManager;

import com.nasri.restjersey.EM;

import Client.com.nasri.restjersy.model.Club;
import Client.com.nasri.restjersy.model.User;
import Server.com.nasri.restjersey.model.Badge;

public class ClubController {
	public Club[] getAllClubs() {
		EntityManager em = EM.getEntityManager();
		try {
			javax.persistence.Query q = em.createQuery("select c from Club as c");
			Club[] u = (Club[]) q.getResultList().toArray(new Club[0]);

			int x = 0;
			x++;

			return u;
		} catch (Exception ex) {
			em.close();
		} finally {
			em.close();
		}
		return null;
	}

	public Club getClub(int id) {
		EntityManager em = EM.getEntityManager();
		try {
			Club club = (Club) em.find(Club.class, id);
			return club;
		} catch (Exception ex) {
			em.close();
		} finally {
			em.close();
		}
		return null;
	}

	public boolean addClub(Club club) {
		EntityManager em = EM.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(club);
			em.getTransaction().commit();

			return true;
		} catch (Exception ex) {
			em.close();
		} finally {
			em.close();
		}
		return false;
	}

	public boolean deleteClub(int id) {
		EntityManager em = EM.getEntityManager();

		Club bb = (Club) em.find(Club.class, id);
		em.getTransaction().begin();
		em.remove(bb);
		em.getTransaction().commit();
		em.close();

		return true;
	}

	public boolean updateClub(Club club) {
		EntityManager em = EM.getEntityManager();
		em.getTransaction().begin();
		em.merge(club);
		em.getTransaction().commit();
		em.close();

		return true;

	}

}
