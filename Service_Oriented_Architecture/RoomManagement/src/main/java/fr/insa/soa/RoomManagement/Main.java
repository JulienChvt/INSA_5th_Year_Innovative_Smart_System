package fr.insa.soa.RoomManagement;

import java.io.IOException;

import fr.insa.soa.client.Client;

public class Main {
	public static void main( String[] args ) throws NumberFormatException, IOException, InterruptedException
    {
        System.out.println( "----------- Room Management Controller -----------" );
        
        Client client = new Client();
        
        while (true){
        	
        	Controller.coolDownRoom(client,"213");    	
        	
        	Controller.CloseRoomAfterWorkingHours(client,"213");
        	
        	Controller.checkPresence(client, "213");
        	
        	System.out.println("");
        	System.out.println("");
        	Thread.sleep(10000);
        }
    }
}
