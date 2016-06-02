package Client.com.nasri.restjersy.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String email;

	private String password;

	private String username;
	
	private int idClub;

	public User() {
	}
	

	public User(String email, String password, String username, int idClub) {
		super();
		this.email = email;
		this.password = password;
		this.username = username;
		this.idClub = idClub;
	}


	public User(int id, String email, String password, String username, int idClub) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.username = username;
		this.idClub = idClub;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getIdClub() {
		return idClub;
	}

	public void setIdClub(int idClub) {
		this.idClub = idClub;
	}
	
	

}