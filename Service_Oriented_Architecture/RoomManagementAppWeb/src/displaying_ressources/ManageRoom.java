package displaying_ressources;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.insa.soa.client.Client;

/**
 * Servlet implementation class ManageRoom
 */

@WebServlet(urlPatterns= {"/ManageRoom","/RefreshProperties"})
public class ManageRoom extends HttpServlet {
	Client cl = new Client();
	
	private static final long serialVersionUID = 1L;
	// Variables to display information on the success.jsp file
	private String commandHistory; //list all the command history
	private String ListOfRoomNumbers; //list all the room numbers
    private RoomProperties myRoom; //allow you to access at the room properties
    private ArrayList<RoomProperties> myListRooms; //list all the room with their properties
    /**
     * @throws IOException 
     * @throws NumberFormatException 
     * @see HttpServlet#HttpServlet()
     */
    //Constructor of the servlet
    public ManageRoom() throws NumberFormatException, IOException {
        super();
        //Initialize all the variables
        commandHistory="Login Success";
        ListOfRoomNumbers = "<option value=213>213";        
        myRoom = new RoomProperties(1);
        myListRooms = new ArrayList<RoomProperties>(); //create the list of rooms
        
        //Manage the room declaration
        int roomId = 213;
        myListRooms.add(new RoomProperties(roomId)); //add room to the list
        
        //Change Temperature values using the setters
    /*   myListRooms.get(0).setIndoorTemp(Integer.valueOf(cl.get("http://localhost:8080/RestServicesRoom/webapi/tempsensorint/GEI_213").getRepresentation()));
        myListRooms.get(0).setOutdoorTemp(Integer.valueOf(cl.get("http://localhost:8080/RestServicesRoom/webapi/tempsensorext/GEI_"+roomId).getRepresentation()));
        myListRooms.get(0).setAlarmState(cl.get("http://localhost:8080/RestServicesRoom/webapi/alarm/GEI_"+roomId).getRepresentation());
        myListRooms.get(0).setDoorState(cl.get("http://localhost:8080/RestServicesRoom/webapi/doorsensor/GEI_213").getRepresentation());
        myListRooms.get(0).setLightState(cl.get("http://localhost:8080/RestServicesRoom/webapi/lightsensor/GEI_213").getRepresentation());
        myListRooms.get(0).setWindowState(cl.get("http://localhost:8080/RestServicesRoom/webapi/windowssensor/GEI_213").getRepresentation());
    */
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Function called every time we send a get request with the webservlet pattern
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		//Check if the action parameter
		if (action.equals("Refresh")) {
			//Refresh all the values of the room
			int roomId = Integer.parseInt(request.getParameter("RoomNumber"));
		    /* myListRooms.get(0).setIndoorTemp(Integer.valueOf(cl.get("http://localhost:8080/RestServicesRoom/webapi/tempsensorint/GEI_"+roomId).getRepresentation()));
		     myListRooms.get(0).setOutdoorTemp(Integer.valueOf(cl.get("http://localhost:8080/RestServicesRoom/webapi/tempsensorext/GEI_"+roomId).getRepresentation()));
	         myListRooms.get(0).setAlarmState(cl.get("http://localhost:8080/RestServicesRoom/webapi/alarm/GEI_"+roomId).getRepresentation());
	         myListRooms.get(0).setDoorState(cl.get("http://localhost:8080/RestServicesRoom/webapi/doorsensor/GEI_"+roomId).getRepresentation());
	         myListRooms.get(0).setLightState(cl.get("http://localhost:8080/RestServicesRoom/webapi/lightsensor/GEI_"+roomId).getRepresentation());
   	         myListRooms.get(0).setWindowState(cl.get("http://localhost:8080/RestServicesRoom/webapi/windowssensor/GEI_"+roomId).getRepresentation());
			*/
			
			//ClientAPI client = new ClientAPI();
			//myListRooms.get(0).setIndoorTemp(client.getIndoorTemp(roomId));
			//Add the command at the history
	        commandHistory+="<br/>Refresh Values";
	        
	        //Set all the attributes for the request
			request.setAttribute("ListRoom", ListOfRoomNumbers); //all the room numbers
			request.setAttribute("RoomProper", selectRoom(Integer.parseInt(request.getParameter("RoomNumber")))); //all the room properties of the chosen room
			request.setAttribute("history", commandHistory); //all the command history
			RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp"); //display the success.jsp file
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Function called every time we send a post request with the webservlet pattern
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Read the request action
		String action = request.getParameter("action");
		int roomId = Integer.parseInt(request.getParameter("RoomNumber"));
		
		//Check the action parameter
		if(action.equals("Close the door")) {
			//Ask to close the door
			//cl.post("http://localhost:8080/RestServicesRoom/webapi/doorsensor/GEI_213/close");
			
			//Update the room properties
			//myListRooms.get(0).setDoorState(cl.get("http://localhost:8080/RestServicesRoom/webapi/doorsensor/GEI_213").getRepresentation());
			
			//Add the command to the history
			commandHistory+="<br/>Door closed";
			
			//Set all the attributes for the request and send the request
			request.setAttribute("ListRoom", ListOfRoomNumbers);
			request.setAttribute("RoomProper", selectRoom(Integer.parseInt(request.getParameter("RoomNumber"))));
			request.setAttribute("history", commandHistory);
			RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
			dispatcher.forward(request, response);
		}                                                                                                                                                                                                                                                                                                                                                                      
		else if (action.equals("Open the door")){
			//Ask to open the door
			//cl.post("http://localhost:8080/RestServicesRoom/webapi/doorsensor/GEI_"+roomId+"/open");
			
			//Update the room properties
			//myListRooms.get(0).setDoorState(cl.get("http://localhost:8080/RestServicesRoom/webapi/doorsensor/GEI_213").getRepresentation());
			
			//Add the command to the history
			commandHistory+="<br/>Door opened";
			
			//Set all the attributes for the request and send the request
			request.setAttribute("ListRoom", ListOfRoomNumbers);
			request.setAttribute("RoomProper", selectRoom(Integer.parseInt(request.getParameter("RoomNumber"))));
			request.setAttribute("history", commandHistory);
			RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
			dispatcher.forward(request, response);
		}
		else if (action.equals("Close the Window")){
			//Ask to close the window
			//cl.post("http://localhost:8080/RestServicesRoom/webapi/windowssensor/GEI_"+roomId+"/close");
			
			//Update the room properties
  	        // myListRooms.get(0).setWindowState(cl.get("http://localhost:8080/RestServicesRoom/webapi/windowssensor/GEI_"+roomId).getRepresentation());
			
			//Add the command to the history
			commandHistory+="<br/>Window closed";
			
			//Set all the attributes for the request and send the request
			request.setAttribute("ListRoom", ListOfRoomNumbers);
			request.setAttribute("RoomProper", selectRoom(Integer.parseInt(request.getParameter("RoomNumber"))));
			request.setAttribute("history", commandHistory);
			RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
			dispatcher.forward(request, response);
		}
		else if (action.equals("Open the Window")){
			//Ask to open the window
		//	cl.post("http://localhost:8080/RestServicesRoom/webapi/windowssensor/GEI_"+roomId+"/open");
			
			//Update the room properties
  	         //myListRooms.get(0).setWindowState(cl.get("http://localhost:8080/RestServicesRoom/webapi/windowssensor/GEI_"+roomId).getRepresentation());
			
			//Add the command to the history
			commandHistory+="<br/>Window opened";
			
			//Set all the attributes for the request and send the request
			request.setAttribute("ListRoom", ListOfRoomNumbers);
			request.setAttribute("RoomProper", selectRoom(Integer.parseInt(request.getParameter("RoomNumber"))));
			request.setAttribute("history", commandHistory);
			RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
			dispatcher.forward(request, response);
		}
		else if (action.equals("Measure Indoor Temperature")){
			//Ask to measure the indoor temperature and Update the room properties
		//	myListRooms.get(0).setIndoorTemp(Integer.valueOf(cl.get("http://localhost:8080/RestServicesRoom/webapi/tempsensorint/GEI_"+roomId).getRepresentation()));
			
			
			//Add the command to the history
			commandHistory+="<br/>Indoor temperature measured";
			
			//Set all the attributes for the request and send the request
			request.setAttribute("ListRoom", ListOfRoomNumbers);
			request.setAttribute("RoomProper", selectRoom(Integer.parseInt(request.getParameter("RoomNumber"))));
			request.setAttribute("history", commandHistory);
			RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
			dispatcher.forward(request, response);
		}
		else if (action.equals("Measure Outdoor Temperature")){
			//Ask to measure the outdoor temperature and Update the room properties
		//	myListRooms.get(0).setOutdoorTemp(Integer.valueOf(cl.get("http://localhost:8080/RestServicesRoom/webapi/tempsensorext/GEI_"+roomId).getRepresentation()));
			
			//Add the command to the history
			commandHistory+="<br/>Outdoor temperature measured";
			
			//Set all the attributes for the request and send the request
			request.setAttribute("ListRoom", ListOfRoomNumbers);
			request.setAttribute("RoomProper", myRoom);
			request.setAttribute("history", commandHistory);
			RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
			dispatcher.forward(request, response);
		}
		else if (action.equals("Turn on light")){
			//Ask to turn on the light
		//	cl.post("http://localhost:8080/RestServicesRoom/webapi/lightsensor/GEI_"+roomId+"/on");
			
			//Update the room properties
		//	myListRooms.get(0).setLightState(cl.get("http://localhost:8080/RestServicesRoom/webapi/lightsensor/GEI_"+roomId).getRepresentation());
			
			//Add the command to the history
			commandHistory+="<br/>Turn on light";
			
			//Set all the attributes for the request and send the request
			request.setAttribute("ListRoom", ListOfRoomNumbers);
			request.setAttribute("RoomProper", selectRoom(Integer.parseInt(request.getParameter("RoomNumber"))));
			request.setAttribute("history", commandHistory);
			RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
			dispatcher.forward(request, response);
		}
		else if (action.equals("Turn off light")){
			//Ask to turn off the light
		//	cl.post("http://localhost:8080/RestServicesRoom/webapi/lightsensor/GEI_"+roomId+"/off");
			
			//Update the room properties
		//	myListRooms.get(0).setLightState(cl.get("http://localhost:8080/RestServicesRoom/webapi/lightsensor/GEI_"+roomId).getRepresentation());
			
			//Add the command to the history
			commandHistory+="<br/>Turn off light";
			
			//Set all the attributes for the request and send the request
			request.setAttribute("ListRoom", ListOfRoomNumbers);
			request.setAttribute("RoomProper", selectRoom(Integer.parseInt(request.getParameter("RoomNumber"))));
			request.setAttribute("history", commandHistory);
			RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	//Select the room inside the list of rooms
	protected RoomProperties selectRoom(int roomNumber) {
		RoomProperties room = null;
			for (RoomProperties e : myListRooms) {
				if(e.getRoomId() == roomNumber) {
					room = e;
				}
			}
		return room;
	}
}
