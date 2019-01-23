package fr.insat.om2m.initRooms.Init;

import java.io.IOException;

import fr.insat.om2m.initRooms.Init.Building;
import fr.insat.om2m.initRooms.Init.VirtualSensor;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		String originator = "admin:admin";
		Building building = new Building();
		VirtualSensor virtSensor = new VirtualSensor();
		
		//Init rooms and their sensors
		building.initRoom(originator, "GEI_213");
		virtSensor.initVirtualSensors(originator, "GEI_213");
		
		//Update value of the indoor & outdoor temperature sensors every 10sec 
		while (true) {
			virtSensor.setVirtualTemperature(originator, "GEI_213");
			Thread.sleep(10000);
		}
	}
}