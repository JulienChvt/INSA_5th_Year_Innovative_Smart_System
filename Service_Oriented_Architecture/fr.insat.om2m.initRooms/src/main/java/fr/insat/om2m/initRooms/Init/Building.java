package fr.insat.om2m.initRooms.Init;

import java.io.IOException;

import org.eclipse.om2m.commons.resource.AE;
import org.eclipse.om2m.commons.resource.Container;

import fr.insat.om2m.initRooms.client.Client;
import fr.insat.om2m.initRooms.client.Response;
import fr.insat.om2m.initRooms.mapper.Mapper;
import fr.insat.om2m.initRooms.mapper.MapperInterface;

public class Building {
	
	static Client cl = new Client();
	static Response rep = new Response();
	static MapperInterface mapper = new Mapper();
	
	public void initRoom(String originator, String room) throws IOException {
		createRoom(originator, room);
		createTempSensorInt(originator, room);
		createTempSensorExt(originator, room);
		createDoorSensor(originator, room);
		createWindowsSensor(originator, room);
		createMvtSensor(originator, room);
		createLightSensor(originator, room);
		createAlarm(originator, room);
	}
	
	public void createRoom(String originator, String room) throws IOException{
		AE ae_room = new AE();
		ae_room.setName(room);
		ae_room.setAppID(room);
		ae_room.setRequestReachability(false);
		String representation = mapper.marshal(ae_room);
		String url = "http://localhost:8081/~/mn-cse/";
		cl.create(url, representation, originator, "2");
		//this.room_name = room;
		System.out.println(room+" created");
	}
	
	public void createTempSensorInt(String originator, String room) throws IOException{
		Container ct = new Container();
		ct.setName("Indoor_Temperature_Sensor");
		String representation = mapper.marshal(ct);
		String url = "http://localhost:8081/~/mn-cse/mn-name/"+room;
		cl.create(url, representation, originator, "3");
		System.out.println("Indoor temperature sensor created in "+room);
	}
	
	public void createTempSensorExt(String originator, String room) throws IOException{
		Container ct = new Container();
		ct.setName("Outdoor_Temperature_Sensor");
		String representation = mapper.marshal(ct);
		String url = "http://localhost:8081/~/mn-cse/mn-name/"+room;
		cl.create(url, representation, originator, "3");
		System.out.println("Outdoor temperature sensor created in "+room);
	}
	
	public void createDoorSensor(String originator, String room) throws IOException{
		Container ct = new Container();
		ct.setName("Door_Sensor");
		String representation = mapper.marshal(ct);
		String url = "http://localhost:8081/~/mn-cse/mn-name/"+room;
		cl.create(url, representation, originator, "3");
		System.out.println("Door sensor created in "+room);
	}
	
	public void createWindowsSensor(String originator, String room) throws IOException{
		Container ct = new Container();
		ct.setName("Windows_Sensor");
		String representation = mapper.marshal(ct);
		String url = "http://localhost:8081/~/mn-cse/mn-name/"+room;
		cl.create(url, representation, originator, "3");
		System.out.println("Windows sensor created in "+room);
	}
	
	public void createMvtSensor(String originator, String room) throws IOException{
		Container ct = new Container();
		ct.setName("Presence_Sensor");
		String representation = mapper.marshal(ct);
		String url = "http://localhost:8081/~/mn-cse/mn-name/"+room;
		cl.create(url, representation, originator, "3");
		System.out.println("Presence sensor created in "+room);
	}
	
	public void createLightSensor(String originator, String room) throws IOException{
		Container ct = new Container();
		ct.setName("Light_Sensor");
		String representation = mapper.marshal(ct);
		String url = "http://localhost:8081/~/mn-cse/mn-name/"+room;
		cl.create(url, representation, originator, "3");
		System.out.println("Light sensor created in "+room);
	}
	
	public void createAlarm(String originator, String room) throws IOException{
		Container ct = new Container();
		ct.setName("Alarm");
		String representation = mapper.marshal(ct);
		String url = "http://localhost:8081/~/mn-cse/mn-name/"+room;
		cl.create(url, representation, originator, "3");
		System.out.println("Alarm created in "+room);
	}
}
