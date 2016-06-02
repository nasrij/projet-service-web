package Server.com.nasri.restjersey.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the points database table.
 * 
 */
@Entity
@Table(name = "points")
@NamedQuery(name = "Point.findAll", query = "SELECT p FROM Point p")
public class Point implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String cause;

	@Temporal(TemporalType.DATE)
	private Date date;

	private int point;

	private int idclub;

	public Point() {
	}

	public Point(String cause, Date date, int point, int idclub) {
		super();
		this.cause = cause;
		this.date = date;
		this.point = point;
		this.idclub = idclub;
	}

	public Point(int id, String cause, Date date, int point, int idclub) {
		super();
		this.id = id;
		this.cause = cause;
		this.date = date;
		this.point = point;
		this.idclub = idclub;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdClub() {
		return this.idclub;
	}

	public void setIdClub(int id) {
		this.idclub = id;
	}

	public String getCause() {
		return this.cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPoint() {
		return this.point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}