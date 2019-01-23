package fr.insa.soa.RoomManagement;

import java.io.IOException;
import java.util.Date;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import fr.insa.soa.client.Client;

public class Controller {
	
	static void coolDownRoom(Client cl, String RoomId) throws NumberFormatException, IOException, InterruptedException {
		//Windows Management
    	int tempInt = Integer.valueOf(cl.get("http://localhost:8080/RestServicesRoom/webapi/tempsensorint/GEI_" + RoomId).getRepresentation());
    	int tempExt = Integer.valueOf(cl.get("http://localhost:8080/RestServicesRoom/webapi/tempsensorext/GEI_" + RoomId).getRepresentation());
    	System.out.println("Indoor temperature: "+tempInt);
    	System.out.println("Outdoor temperature: "+tempExt);
    	
    	if (tempExt < tempInt && 17<tempExt && tempExt<28){
    		cl.post("http://localhost:8080/RestServicesRoom/webapi/windowssensor/GEI_"+ RoomId + "/open");
    		System.out.println("Too hot! Openning windows!");
    	} else{
    		String windows = cl.get("http://localhost:8080/RestServicesRoom/webapi/windowssensor/GEI_" + RoomId).getRepresentation();
    		if (windows.equals("open")){
    			cl.post("http://localhost:8080/RestServicesRoom/webapi/windowssensor/GEI_"+ RoomId + "/close");
        		System.out.println("Too cold! Closing windows!");
    		}
    	}
	}
	
	static void CloseRoomAfterWorkingHours(Client cl, String RoomId) throws NumberFormatException, IOException, InterruptedException {
		//Close doors, windows and lights if hour < 8h || hour > 18h && there is nobody
    	Date dt = new Date();
    	int currentHour = dt.getHours();
    	String presence = cl.get("http://localhost:8080/RestServicesRoom/webapi/presencesensor/GEI_" + RoomId).getRepresentation();
    	System.out.println("Current hour: "+currentHour);
    	System.out.println("Presence sensor: "+presence);
    	if (currentHour < 8 || currentHour > 18){
    		if (presence.equals("false")){
    			cl.post("http://localhost:8080/RestServicesRoom/webapi/doorsensor/GEI_"+RoomId+"/close");
    			cl.post("http://localhost:8080/RestServicesRoom/webapi/windowssensor/GEI_"+RoomId+"/close");
    			cl.post("http://localhost:8080/RestServicesRoom/webapi/lightsensor/GEI_"+RoomId+"/off");
    			System.out.println("Out of working hours and nobody in the room => Closing doors an windows and switching the lights off");
    		}
    	}
	}
	
	static void checkPresence(Client cl, String RoomId) throws NumberFormatException, IOException, InterruptedException {
		Date dt = new Date();
    	int currentHour = dt.getHours();
    	String presence = cl.get("http://localhost:8080/RestServicesRoom/webapi/presencesensor/GEI_" + RoomId).getRepresentation();
		//Alarm: if there is someone after 22h => Turn on the alarm
    	if (currentHour > 22 && presence.equals("true")){
    		cl.post("http://localhost:8080/RestServicesRoom/webapi/alarm/GEI_" + RoomId + "/on");
    		System.out.println("Alarm ON!");
    	}
	}
}
