package Server.com.nasri.restjersey.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the badge database table.
 * 
 */
@Entity
@NamedQuery(name="Badge.findAll", query="SELECT b FROM Badge b")
public class Badge implements Serializable {
	private static final long serialVersionUID = 1L;

	public Badge(String image, String nomBadge, int pointMax, int pointMin) {
		super();
		this.image = image;
		this.nomBadge = nomBadge;
		this.pointMax = pointMax;
		this.pointMin = pointMin;
	}
	
	public Badge(int id ,String image, String nomBadge, int pointMax, int pointMin) {
		super();
		this.image = image;
		this.nomBadge = nomBadge;
		this.pointMax = pointMax;
		this.pointMin = pointMin;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String image;

	private String nomBadge;

	private int pointMax;

	private int pointMin;

	public Badge() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	public String getNomBadge() {
		return this.nomBadge;
	}

	public void setNomBadge(String nomBadge) {
		this.nomBadge = nomBadge;
	}

	public int getPointMax() {
		return this.pointMax;
	}

	public void setPointMax(int pointMax) {
		this.pointMax = pointMax;
	}

	public int getPointMin() {
		return this.pointMin;
	}

	public void setPointMin(int pointMin) {
		this.pointMin = pointMin;
	}

}