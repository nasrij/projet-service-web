package Server.com.nasri.restjersey.controller;

import java.util.List;

import javax.persistence.EntityManager;

import com.nasri.restjersey.EM;

import Client.com.nasri.restjersy.model.Evenement;
import Client.com.nasri.restjersy.model.User;
import Server.com.nasri.restjersey.model.Point;

public class PointController {
	public Point[] getAllPoints() {
		EntityManager em = EM.getEntityManager();
		try {
			javax.persistence.Query q = em.createQuery("select c from Point as c group by  c.idclub order by sum(c.point) ");
			Point[] u = (Point[]) q.getResultList().toArray(new Point[0]);

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
	
	public List<?> getClubPoints() {
		EntityManager em = EM.getEntityManager();
		try {
			javax.persistence.Query q = em.createQuery("select p.idclub, c.nom ,c.image, Sum(p.point) from Point as p , Club as c where p.idclub = c.id group by  p.idclub , c.nom ,c.image order by sum(p.point) desc ");
	        List<?> list = q.getResultList();

			//Point[] u = (Point[]) q.getResultList().toArray(new Point[0]);

			int x = 0;
			x++;

			return list;
		} catch (Exception ex) {
			em.close();
		} finally {
			em.close();
		}
		return null;
	}

	public Point getPoint(int id) {
		EntityManager em = EM.getEntityManager();
		try {
			Point point = em.find(Point.class, id);
			return point;
		} catch (Exception ex) {
			em.close();
		} finally {
			em.close();
		}
		return null;
	}

	public boolean addPoint(Point point) {
		EntityManager em = EM.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(point);
			em.getTransaction().commit();

			return true;
		} catch (Exception ex) {
			em.close();
		} finally {
			em.close();
		}
		return false;
	}

	public boolean deletePoint(int id) {
		EntityManager em = EM.getEntityManager();
		Point ee = (Point) em.find(Point.class, id);
		em.getTransaction().begin();
		em.remove(ee);
		em.getTransaction().commit();
		return true;

	}

	public boolean updatePoint(Point p) {
		EntityManager em = EM.getEntityManager();
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
		return true;

	}

	public Long getPointsClub(int idClub) {
		EntityManager em = EM.getEntityManager();
		try {
			javax.persistence.Query q = em.createQuery("select sum(p.point) from Point as p  where  p.idclub = :id ");
			q.setParameter("id", idClub);
			Long o = (Long) q.getSingleResult();
			return o;

		} catch (Exception ex) {
			em.close();
		} finally {
			em.close();
		}
		return null;
	}
}
