package fr.insa.soa.RestServicesRoom;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import fr.insat.om2m.tp2.client.Client;
import fr.insat.om2m.tp2.client.Response;


@Path("tempsensorext")
public class TempSensorExt {
	
	//Client client = ClientBuilder.newClient();
	Client client = new Client();
	String originator = "admin:admin";
	
	 @GET
	 @Produces(MediaType.TEXT_PLAIN)
	 public String hello() {
		 return "Outdoor temperature sensor";
	 }
	
	@GET
	@Path("/{room}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTempExt(@PathParam("room") String room) throws IOException{
		String url = "http://127.0.0.1:8081/~/mn-cse/mn-name/"+room+"/Outdoor_Temperature_Sensor/la";
		//Response response = client.target(url).request().header("X-M2M-Origin", originator).get();
		Response response = client.retrieve(url, originator);
		JSONObject obj = new JSONObject(response.getRepresentation());
		String con = obj.getJSONObject("m2m:cin").getString("con");
		return con;
	}
}
