package Client.com.nasri.restjersy.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import Client.com.nasri.restjersy.Controller.EvenementController;
import Client.com.nasri.restjersy.model.Club;
import Client.com.nasri.restjersy.model.Evenement;

@Path("/Evenement")
public class EvenementService {

	@GET
	@Path("/getAll/{id}")
	public Response getTout(@PathParam("id") int id) {
		JSONObject jsonObject = new JSONObject();
		EvenementController uc = new EvenementController();
		Evenement[] evenements = uc.getAllEvenements();
		jsonObject.put("nb", evenements.length);
		jsonObject.put("users", evenements);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/add")
	public Response addEvenement(@FormParam("image") String image,@FormParam("description") String description, @FormParam("date") String date,
			@FormParam("heure") String heure, @FormParam("latitude") String latitude,
			@FormParam("longitude") String longitude, @FormParam("lieux") String lieux, @FormParam("nom") String nom, @FormParam("idClub") String idClub) {
		EvenementController uc = new EvenementController();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

		Date d = null;
		try {
			d = format1.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Evenement evenement = new Evenement(image ,d, description, heure, latitude, lieux, longitude, nom,Integer.parseInt(idClub));
		Boolean r = uc.addEvenement(evenement);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", r);
		String result = "" + jsonObject;
		return getNoCacheResponseBuilder(Response.Status.OK).entity(result).build();
	}

	@POST
	@Path("/update")
	@Produces("application/json")
	public Response updateClub(@FormParam("id") String id, @FormParam("description") String description,
			@FormParam("date") String date, @FormParam("heure") String heure, @FormParam("latitude") String latitude,
			@FormParam("longitude") String longitude, @FormParam("lieux") String lieux, @FormParam("nom") String nom) {
		EvenementController uc = new EvenementController();
		EntityManager em = EM.getEntityManager();
		Evenement e=(Evenement) em.find(Evenement.class, Integer.parseInt(id));		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

		Date d = null;
		try {
			d = format1.parse(date);
		} catch (ParseException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		e.setDate(d);
		e.setDescription(description);
		e.setHeure(heure);
		e.setLatitude(latitude);
		e.setLongitude(longitude);
		e.setLieux(lieux);
		e.setNom(nom);
		Boolean r = uc.updateEvenement(e);
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
	public Response deleteEvenement(@PathParam("id") int id) {
		JSONObject jsonObject = new JSONObject();
		EvenementController uc = new EvenementController();
		Boolean r = uc.deleteEvenement(id);
		jsonObject.put("result", r);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("{id}")
	public Response findEvenement(@PathParam("id") int id) {

		JSONObject jsonObject = new JSONObject();
		JSONObject json = new JSONObject();
		EvenementController uc = new EvenementController();
		Evenement r = uc.getEvenement(id);
		json.put("id", r.getId());
		json.put("date", r.getDate().toString());
		json.put("description", r.getDescription());
		json.put("heure", r.getHeure());
		json.put("latitude", r.getLatitude());
		json.put("longitude", r.getLongitude());
		json.put("lieux", r.getLieux());
		json.put("nom", r.getNom());
		jsonObject.put("result", json);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}

}
