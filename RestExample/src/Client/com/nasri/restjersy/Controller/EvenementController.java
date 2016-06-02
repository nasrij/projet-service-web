package Client.com.nasri.restjersy.Controller;

import javax.persistence.EntityManager;

import com.nasri.restjersey.EM;

import Client.com.nasri.restjersy.model.Club;
import Client.com.nasri.restjersy.model.Evenement;
import Client.com.nasri.restjersy.model.User;

public class EvenementController {
	public Evenement[] getAllEvenements() {
		EntityManager em = EM.getEntityManager();
		try {
			javax.persistence.Query q = em.createQuery("select c from Evenement as c order by c.date asc");
			Evenement[] u = (Evenement[]) q.getResultList().toArray(new Evenement[0]);

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

	public Evenement getEvenement(int id) {
		EntityManager em = EM.getEntityManager();
		try {
			Evenement evenement = em.find(Evenement.class, id);
			return evenement;
		} catch (Exception ex) {
			em.close();
		} finally {
			em.close();
		}
		return null;
	}

	public boolean addEvenement(Evenement evenement) {
		EntityManager em = EM.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(evenement);
			em.getTransaction().commit();

			return true;
		} catch (Exception ex) {
			em.close();
		} finally {
			em.close();
		}
		return false;
	}

	public boolean deleteEvenement(int id) {
		EntityManager em = EM.getEntityManager();

		em.getTransaction().begin();
		Evenement ee = (Evenement) em.find(Evenement.class, id);
		em.remove(ee);
		em.getTransaction().commit();
		return true;
	}

	public boolean updateEvenement(Evenement ee) {
		EntityManager em = EM.getEntityManager();
		em.getTransaction().begin();
		em.merge(ee);
		em.getTransaction().commit();
		return true;
	}

}
