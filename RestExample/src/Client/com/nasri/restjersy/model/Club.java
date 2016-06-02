package Client.com.nasri.restjersy.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the club database table.
 * 
 */
@Entity
@NamedQuery(name = "Club.findAll", query = "SELECT c FROM Club c")
public class Club implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String description;

	private String image;

	private String nom;

	private String slogan;

	public Club() {
	}

	public Club(String description, String image, String nom, String slogan) {
		super();
		this.description = description;
		this.image = image;
		this.nom = nom;
		this.slogan = slogan;
	}

	public Club(int id, String description, String image, String nom, String slogan) {
		super();
		this.id = id;
		this.description = description;
		this.image = image;
		this.nom = nom;
		this.slogan = slogan;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getSlogan() {
		return this.slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

}