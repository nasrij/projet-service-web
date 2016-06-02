package Client.com.nasri.restjersy.Service;

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

import Client.com.nasri.restjersy.Controller.ClubBadgeController;
import Client.com.nasri.restjersy.Controller.ClubController;
import Client.com.nasri.restjersy.model.Club;
import Client.com.nasri.restjersy.model.Clubbadge;
import Server.com.nasri.restjersey.model.Badge;

@Path("/clubBadge")
public class ClubBadgeService {
	
	@POST
	@Path("/add")
	@Produces("application/json")
	public Response addUser(@FormParam("idClub") int idClub, @FormParam("idBadge") int idBadge) {
		ClubBadgeController cb = new ClubBadgeController();
		Clubbadge cbb = new Clubbadge(idBadge, idClub);
		
		Boolean r = cb.addClubBadge(cbb);
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
	public Response deleteClubBadge(@PathParam("id") int id) {
		JSONObject jsonObject = new JSONObject();
		ClubBadgeController uc = new ClubBadgeController();
		Boolean r = uc.deleteClubBadge(id);
		jsonObject.put("result", r);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Produces("application/json")
	@Path("/getAll/{id}")
	public Response getAll(@PathParam("id") int id) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		ClubBadgeController cbc = new ClubBadgeController();
		Badge[] badges = cbc.getClubBadges(id);
		jsonObject.put("nb", badges.length);
		jsonObject.put("badges", badges);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}



}
