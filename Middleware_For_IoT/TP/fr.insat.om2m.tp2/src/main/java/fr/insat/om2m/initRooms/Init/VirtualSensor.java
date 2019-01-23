package fr.insat.om2m.initRooms.Init;

import java.io.IOException;
import java.util.Random;

import org.eclipse.om2m.commons.resource.ContentInstance;

import fr.insat.om2m.initRooms.client.Client;
import fr.insat.om2m.initRooms.client.Response;
import fr.insat.om2m.initRooms.mapper.Mapper;
import fr.insat.om2m.initRooms.mapper.MapperInterface;

public class VirtualSensor {
	
	static Client cl = new Client();
	static Response rep = new Response();
	static MapperInterface mapper = new Mapper();
	
	
	//Create a new cin representing the value of the temperature sensor, in the room given in parameter, by a random value in range (15, 25)
	public void setVirtualTemperature(String originator, String room) throws IOException {		
		ContentInstance cinTempInt = new ContentInstance();
		ContentInstance cinTempOut = new ContentInstance();
		
		//Generate random temperature
		int randomTemp = new Random().nextInt(11) + 15; //Generate random value between 15 and 25
		
		//Generate a random delta
		int randomDelta = new Random().nextInt(7)-3;
		
		//Create the xml for the ressource of the tempInt
		cinTempInt.setName("Temperature_Sensor_Data_" + Math.abs(new Random().nextInt()));
		cinTempInt.setContent(String.valueOf(randomTemp));
		String representationTempInt = mapper.marshal(cinTempInt);
		
		//Create the xml for the ressource of the tempOut
		cinTempOut.setName("Temperature_Sensor_Data_" + Math.abs(new Random().nextInt()));
		cinTempOut.setContent(String.valueOf(randomTemp+randomDelta));
		String representationTempOut = mapper.marshal(cinTempOut);
		
		//Create a new cin for indoor sensor
		String url = "http://127.0.0.1:8081/~/mn-cse/mn-name/"+room+"/Indoor_Temperature_Sensor/"; 
		cl.create(url, representationTempInt, originator, "4");
		System.out.println("Virtual indoor temperature sent = " +randomTemp);
		
		//Create a new cin for outdoor sensor
		url = "http://127.0.0.1:8081/~/mn-cse/mn-name/"+room+"/Outdoor_Temperature_Sensor/"; 
		cl.create(url, representationTempOut, originator, "4");
		System.out.println("Virtual outdoor temperature sent = " +(randomTemp+randomDelta));
	}
	
	public void initVirtualSensors(String originator, String room) throws IOException {
		setVirtualDoor(originator, room);
		setVirtualMvt(originator, room);
		setVirtualWindows(originator, room);
		setVirtualLight(originator, room);
		setVirtualAlarm(originator, room);
	}
	
	public void setVirtualDoor(String originator, String room) throws IOException {
		ContentInstance cin = new ContentInstance();
		
		//Create the xml for the ressource
		cin.setName("Door_Sensor_Data_" + Math.abs(new Random().nextInt()));
		cin.setContent("open");
		String representation = mapper.marshal(cin);
		
		//Create a new cin 
		String url = "http://127.0.0.1:8081/~/mn-cse/mn-name/"+room+"/Door_Sensor/"; 
		cl.create(url, representation, originator, "4");
		System.out.println("Door sensor updated for "+room);
	}
	
	public void setVirtualWindows(String originator, String room) throws IOException {
		ContentInstance cin = new ContentInstance();

		//Create the xml for the ressource
		cin.setName("Windows_Sensor_Data_" + Math.abs(new Random().nextInt()));
		cin.setContent("open");
		String representation = mapper.marshal(cin);
		
		//Create a new cin
		String url = "http://127.0.0.1:8081/~/mn-cse/mn-name/"+room+"/Windows_Sensor/"; 
		cl.create(url, representation, originator, "4");
		System.out.println("Windows sensor updated for "+room);
				
	}
	
	public void setVirtualMvt(String originator, String room) throws IOException {
		ContentInstance cin = new ContentInstance();
		
		//Create the xml for the ressource
		cin.setName("Presence_Sensor_Data_" + Math.abs(new Random().nextInt()));
		cin.setContent("true");
		String representation = mapper.marshal(cin);
		
		//Create a new cin
		String url = "http://127.0.0.1:8081/~/mn-cse/mn-name/"+room+"/Presence_Sensor/"; 
		cl.create(url, representation, originator, "4");
		System.out.println("Presence sensor updated for "+room);
	}
	
	public void setVirtualLight(String originator, String room) throws IOException {
		ContentInstance cin = new ContentInstance();
		
		//Create the xml for the ressource
		cin.setName("Light_Sensor_Data_" + Math.abs(new Random().nextInt()));
		cin.setContent("on");
		String representation = mapper.marshal(cin);
		
		//Create a new cin
		String url = "http://127.0.0.1:8081/~/mn-cse/mn-name/"+room+"/Light_Sensor/"; 
		cl.create(url, representation, originator, "4");
		System.out.println("Light sensor updated for "+room);
	}
	
	public void setVirtualAlarm(String originator, String room) throws IOException {
		ContentInstance cin = new ContentInstance();
		
		//Create the xml for the ressource
		cin.setName("Alarm_Data" + Math.abs(new Random().nextInt()));
		cin.setContent("off");
		String representation = mapper.marshal(cin);
		
		//Create a new cin 
		String url = "http://127.0.0.1:8081/~/mn-cse/mn-name/"+room+"/Alarm/"; 
		cl.create(url, representation, originator, "4");
		System.out.println("Alarm updated for "+room);
	}
}
