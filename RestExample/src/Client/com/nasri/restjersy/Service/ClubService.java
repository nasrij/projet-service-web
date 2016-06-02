package Client.com.nasri.restjersy.Service;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import com.nasri.restjersey.EM;

import Client.com.nasri.restjersy.Controller.ClubController;
import Client.com.nasri.restjersy.model.Club;
import Server.com.nasri.restjersey.controller.BadgeController;
import Server.com.nasri.restjersey.model.Badge;

@Path("/club")
public class ClubService {

	@GET
	@Produces("application/json")
	@Path("/getAll/{id}")
	public Response getTout(@PathParam("id") int id) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		ClubController cc = new ClubController();
		Club[] clubs = cc.getAllClubs();
		jsonObject.put("nb", clubs.length);
		jsonObject.put("users", clubs);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/add")
	public Response addClub(@FormParam("description") String description, @FormParam("image") String image,
			@FormParam("nom") String nom, @FormParam("slogan") String slogan) {
		ClubController uc = new ClubController();
		Club club = new Club(description, image, nom, slogan);
		Boolean r = uc.addClub(club);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", r);
		String result = "" + jsonObject;
		return getNoCacheResponseBuilder(Response.Status.OK).entity(result).build();
	}

	@POST
	@Path("/update")
	@Produces("application/json")
	public Response updateClub(@FormParam("id") int id, @FormParam("description") String description,
			@FormParam("image") String image, @FormParam("nom") String nom, @FormParam("slogan") String slogan) {
		ClubController uc = new ClubController();
		EntityManager em = EM.getEntityManager();
		Club club=(Club) em.find(Club.class, id);		
		club.setDescription(description);
		club.setImage(image);
		club.setNom(nom);
		club.setSlogan(slogan);
		Boolean r = uc.updateClub(club);
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
	public Response deleteClub(@PathParam("id") int id) {
		JSONObject jsonObject = new JSONObject();
		ClubController uc = new ClubController();
		Boolean r = uc.deleteClub(id);
		jsonObject.put("result", r);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("{id}")
	public Response findClub(@PathParam("id") int id) {
		JSONObject jsonObject = new JSONObject();
		JSONObject json = new JSONObject();
		ClubController uc = new ClubController();
		Club r = uc.getClub(id);
		json.put("id", r.getId());
		json.put("description", r.getDescription());
		json.put("image", r.getImage());
		json.put("nom", r.getNom());
		json.put("slogan", r.getSlogan());
		jsonObject.put("result", json);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}
}
