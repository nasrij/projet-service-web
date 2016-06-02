package Server.com.nasri.restjersey.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import Client.com.nasri.restjersy.Controller.ClubController;
import Client.com.nasri.restjersy.model.Club;
import Client.com.nasri.restjersy.model.Evenement;
import Server.com.nasri.restjersey.controller.PointController;
import Server.com.nasri.restjersey.model.Point;

@Path("/point")
public class PointService {
	@GET
	@Produces("application/json")
	@Path("/getAll/{id}")
	public Response getTout(@PathParam("id") int id) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		PointController cc = new PointController();
		Point[] points = cc.getAllPoints();
		jsonObject.put("nb", points.length);
		jsonObject.put("users", points);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/add")
	public Response addPoint(@FormParam("points") String point, @FormParam("cause") String cause,
			@FormParam("idClub") String idClub, @FormParam("date") String date) {
		PointController pc = new PointController();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

		Date d = null;
		try {
			d = format1.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Point p = new Point(cause, d, Integer.parseInt(point), Integer.parseInt(idClub));
		Boolean r = pc.addPoint(p);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", r);
		String result = "" + jsonObject;
		return getNoCacheResponseBuilder(Response.Status.OK).entity(result).build();
	}

	@POST
	@Path("/update")
	@Produces("application/json")
	public Response updateClub(@FormParam("id") String id, @FormParam("points") String point,
			@FormParam("cause") String cause, @FormParam("idClub") String idClub, @FormParam("date") String date) {
		PointController pc = new PointController();
		EntityManager em = EM.getEntityManager();
		Point p=(Point) em.find(Point.class, id);	
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

		Date d = null;
		try {
			d = format1.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setCause(cause);
		p.setDate(d);
		p.setIdClub(Integer.parseInt(idClub));
		p.setPoint(Integer.parseInt(point));
		Boolean r = pc.updatePoint(p);
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
		JSONObject jsonObject = new JSONObject();
		PointController pc = new PointController();
		Boolean r = pc.deletePoint(id);
		jsonObject.put("result", r);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("{id}")
	public Response findPoint(@PathParam("id") int id) {
		JSONObject jsonObject = new JSONObject();
		JSONObject json = new JSONObject();
		PointController pc = new PointController();
		id++;
		Point p = pc.getPoint(id);
		json.put("id", p.getId());
		json.put("idClub", p.getIdClub());
		json.put("points", p.getPoint());
		json.put("cause", p.getCause());
		json.put("date", p.getDate());
		jsonObject.put("result", json);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/club/{id}")
	public Response pointClub(@PathParam("id") int id) {
		JSONObject jsonObject = new JSONObject();
		JSONObject json = new JSONObject();
		PointController pc = new PointController();
		Long nb = pc.getPointsClub(id);
		json.put("idClub", id);
		json.put("nbPoint", nb);
		jsonObject.put("result", json);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path("/clubs/{id}")
	public Response classement(@PathParam("id") int id) {
		JSONObject jsonObject = new JSONObject();
		JSONObject json = new JSONObject();
		PointController pc = new PointController();
		List<?> l = pc.getClubPoints();
		json.put("clubs", l);
		json.put("nb", l.size());
		
		jsonObject.put("result", json);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}

}
