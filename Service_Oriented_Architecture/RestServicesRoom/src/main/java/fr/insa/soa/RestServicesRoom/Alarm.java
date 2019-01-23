package fr.insa.soa.RestServicesRoom;

import java.io.IOException;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.om2m.commons.resource.ContentInstance;
import org.json.JSONObject;

import fr.insat.om2m.tp2.client.Client;
import fr.insat.om2m.tp2.client.Response;
import fr.insat.om2m.tp2.mapper.Mapper;
import fr.insat.om2m.tp2.mapper.MapperInterface;

@Path("alarm")
public class Alarm {
	Client client = new Client();
	MapperInterface mapper = new Mapper();
	String originator = "admin:admin";
	
	 @GET
	 @Produces(MediaType.TEXT_PLAIN)
	 public String hello() {
		 return "Alarm sensor";
	 }
	
	@GET
	@Path("/{room}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAlarmState(@PathParam("room") String room) throws IOException{
		String url = "http://127.0.0.1:8081/~/mn-cse/mn-name/"+room+"/Alarm/la";
		Response response = client.retrieve(url, originator);
		JSONObject obj = new JSONObject(response.getRepresentation());
		String con = obj.getJSONObject("m2m:cin").getString("con");
		return con;
	}
	
	@POST
	@Path("/{room}/{state}")
	@Consumes(MediaType.TEXT_PLAIN)
	public String setAlarmState(@PathParam("room") String room, @PathParam("state") String state) throws IOException{
		String url = "http://127.0.0.1:8081/~/mn-cse/mn-name/"+room+"/Alarm/";
		ContentInstance cin = new ContentInstance();
		cin.setName("Alarm_Data_" + Math.abs(new Random().nextInt()));
		cin.setContent(state);
		String representation = mapper.marshal(cin);
		Response response = client.create(url, representation, originator, "4");
		JSONObject obj = new JSONObject(response.getRepresentation());
		String con = obj.getJSONObject("m2m:cin").getString("con");
		return con;
	}
}
