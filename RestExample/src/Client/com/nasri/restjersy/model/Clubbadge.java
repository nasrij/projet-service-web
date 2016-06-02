package Client.com.nasri.restjersy.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the clubbadge database table.
 * 
 */
@Entity
@NamedQuery(name="Clubbadge.findAll", query="SELECT c FROM Clubbadge c")
public class Clubbadge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int idBadge;

	private int idClub;

	public Clubbadge() {
	}

	public Clubbadge(int idBadge, int idClub) {
		super();
		this.idBadge = idBadge;
		this.idClub = idClub;
	}

	public Clubbadge(int id, int idBadge, int idClub) {
		super();
		this.id = id;
		this.idBadge = idBadge;
		this.idClub = idClub;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdBadge() {
		return this.idBadge;
	}

	public void setIdBadge(int idBadge) {
		this.idBadge = idBadge;
	}

	public int getIdClub() {
		return this.idClub;
	}

	public void setIdClub(int idClub) {
		this.idClub = idClub;
	}

}