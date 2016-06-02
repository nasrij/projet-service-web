package Client.com.nasri.restjersy.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the evenement database table.
 * 
 */
@Entity
@NamedQuery(name = "Evenement.findAll", query = "SELECT e FROM Evenement e")
public class Evenement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String description;

	private String heure;

	private String latitude;

	private String lieux;

	private String longitude;

	private String nom;

	private String image;
	
	private int idClub;

	public Evenement() {
	}

	public Evenement(Date date, String description, String heure, String latitude, String lieux, String longitude,
			String nom, int idClub ) {
		super();
		this.date = date;
		this.description = description;
		this.heure = heure;
		this.latitude = latitude;
		this.lieux = lieux;
		this.longitude = longitude;
		this.nom = nom;
		this.idClub = idClub;
	}
	
	public Evenement(String image , Date date, String description, String heure, String latitude, String lieux, String longitude,
			String nom, int idClub ) {
		super();
		this.date = date;
		this.description = description;
		this.heure = heure;
		this.latitude = latitude;
		this.lieux = lieux;
		this.longitude = longitude;
		this.nom = nom;
		this.idClub = idClub;
		this.image = image;
	}

	public Evenement(int id, Date date, String description, String heure, String latitude, String lieux,
			String longitude, String nom, int idClub) {
		super();
		this.id = id;
		this.date = date;
		this.description = description;
		this.heure = heure;
		this.latitude = latitude;
		this.lieux = lieux;
		this.longitude = longitude;
		this.nom = nom;
		this.idClub = idClub;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHeure() {
		return this.heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLieux() {
		return this.lieux;
	}

	public void setLieux(String lieux) {
		this.lieux = lieux;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getIdClub() {
		return idClub;
	}

	public void setIdClub(int idClub) {
		this.idClub = idClub;
	}
	

}