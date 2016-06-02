package Client.com.nasri.restjersy.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import Client.com.nasri.restjersy.Controller.UserController;
import Client.com.nasri.restjersy.model.User;
import Server.com.nasri.restjersey.controller.PointController;

@Path("/user1")
public class GetAllUsers {
	
		@GET
		@Produces("application/json")
		@Path("/getAll")
	  public Response getAll() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		UserController uc = new UserController();
		User[] users = uc.getAllUsers();
		jsonObject.put("nb", users.length); 
		jsonObject.put("users", users);
		String result = ""+ jsonObject;
		PointController p =new PointController();
		Long o = p.getPointsClub(1);
		return Response.status(200).entity(result).build();
	  }
		@POST
		@Path("{user}")
	    @Consumes(MediaType.APPLICATION_JSON)
		public Response addUser(User user)
		{
			UserController uc = new UserController();
			
			User result = null;
			return Response.status(200).entity(result).build();
		}
		

}
