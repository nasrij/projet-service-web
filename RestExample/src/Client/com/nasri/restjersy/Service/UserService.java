package Client.com.nasri.restjersy.Service;


import javax.persistence.EntityManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import com.nasri.restjersey.EM;

import Client.com.nasri.restjersy.Controller.UserController;
import Client.com.nasri.restjersy.model.User;
import Server.com.nasri.restjersey.model.Badge;
import Server.com.nasri.restjersey.model.Point;

@Path("/user")
public class UserService {
	
	@GET
	@Produces("application/json")
	@Path("/getAll")
	public Response getAll() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		UserController cc = new UserController();
		User[] users = cc.getAllUsers();
		jsonObject.put("nb", users.length);
		jsonObject.put("users", users);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}
	
	@POST
	@Path("/add")
	@Produces("application/json")
	public Response addUser(@FormParam("email") String email, @FormParam("password") String password,
			@FormParam("username") String username, @FormParam("idClub") int idClub) {
		UserController uc = new UserController();
		User user = new User(email, password, username, idClub);
		Boolean r = uc.addUser(user);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", r);
		String result = "" + jsonObject;
		return getNoCacheResponseBuilder(Response.Status.OK).entity(result).build();
	}
	
	@POST
	@Path("/update")
	@Produces("application/json")
	public Response updateClub(@FormParam("id") int id, @FormParam("email") String email, @FormParam("password") String password,
			@FormParam("username") String username, @FormParam("idClub") int idClub) {
		UserController uc = new UserController();
		EntityManager em = EM.getEntityManager();
		User u=(User) em.find(User.class, id);	
		u.setEmail(email);
		u.setIdClub(idClub);
		u.setPassword(password);
		u.setUsername(username);
		Boolean r = uc.updateUser(u);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", r);
		String result = "" + jsonObject;
		return getNoCacheResponseBuilder(Response.Status.OK).entity(result).build();
	}

	protected ResponseBuilder getNoCacheResponseBuilder(Response.Status status) {
		CacheControl cc = new CacheControl();
		cc.setNoCache(true);
		cc.setMaxAge(-1);
		cc.setMustRevalidate(true);
		return Response.status(status).cacheControl(cc);
	}
	
	@DELETE
	@Path("{id}")
	public Response deletePoint(@PathParam("id") int id) {
		EntityManager em = EM.getEntityManager();
		JSONObject jsonObject = new JSONObject();
		UserController pc = new UserController();
		User bb=(User) em.find(User.class, id);
		Boolean r = pc.deleteUser(bb);
		jsonObject.put("result", r);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("{id}")
	public Response findUser(@PathParam("id") int id) {
		JSONObject jsonObject = new JSONObject();
		JSONObject json = new JSONObject();
		UserController uc = new UserController();
		User user = uc.getUser(id);
		json.put("id", user.getId());
		json.put("idClub", user.getIdClub());
		json.put("email", user.getEmail());
		json.put("password", user.getPassword());
		json.put("username", user.getUsername());
		jsonObject.put("result", json);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}

}
