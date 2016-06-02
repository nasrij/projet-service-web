package Server.com.nasri.restjersey.service;

import java.io.InputStream;

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

import Client.com.nasri.restjersy.model.Clubbadge;
import Server.com.nasri.restjersey.controller.BadgeController;
import Server.com.nasri.restjersey.model.Badge;

@Path("/badge")
public class BadgeService {
	@GET
	@Produces("application/json")
	@Path("/getAll/{id}")
	public Response getTout(@PathParam("id") int id) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		BadgeController uc = new BadgeController();
		Badge[] badges = uc.getAllBadges();
		jsonObject.put("nb", badges.length);
		jsonObject.put("users", badges);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}

	// *************************************

	@POST
	@Path("/add")
	@Produces("application/json")
	public Response addBadge(@FormParam("image") String imageBadge, @FormParam("nomBadge") String nomBadge) {
		BadgeController uc = new BadgeController();
		Badge badge = new Badge(imageBadge, nomBadge, 0, 0);
		Boolean r = uc.addBadge(badge);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", r);
		String result = "" + jsonObject;
		return getNoCacheResponseBuilder(Response.Status.OK).entity(result).build();
	}
	
	@POST
	@Path("/update")
	@Produces("application/json")
	public Response updateBadge(@FormParam("id") String id , @FormParam("image") String imageBadge, 
			@FormParam("nomBadge") String nomBadge) {
		BadgeController uc = new BadgeController();
        EntityManager em = EM.getEntityManager();
		Badge badge=(Badge) em.find(Badge.class, id);		
		badge.setImage(imageBadge);
		badge.setPointMax(0);
		badge.setNomBadge(nomBadge);
		badge.setPointMin(0);
		Boolean r = uc.updateBadge(badge);
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
	public Response deleteBadge(@PathParam("id") int id) {
		JSONObject jsonObject = new JSONObject();
		BadgeController uc = new BadgeController();
		Boolean r = uc.deleteBadge(id);
		jsonObject.put("result", r);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("{id}")
	public Response findBadge(@PathParam("id") int id) {

		JSONObject jsonObject = new JSONObject();
		JSONObject json = new JSONObject();
		BadgeController uc = new BadgeController();
		Badge r = uc.getBadge(id);
		// jsonObject.put("badge", r);
		json.put("id", r.getId());
		json.put("image", r.getImage());
		json.put("nomBadge", r.getNomBadge());
		json.put("pointMax", r.getPointMax());
		json.put("pointMin", r.getPointMin());
		jsonObject.put("result", json);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}

}
