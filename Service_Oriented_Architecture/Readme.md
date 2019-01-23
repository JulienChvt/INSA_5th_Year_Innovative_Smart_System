## Project : Automatic management of INSA's rooms


# Subject

**Automation of the INSA's rooms management**  

You are requested to develop a Web application (Proof-of-Concept) for managing INSA's rooms . This application must allow automatic closing windows, doors, turning on heating, turning off lights ... etc. This application relies on software services, sensors, and actuators. The goal is to retrieve data from sensors and analyze them to enable taking decisions. For instance, through software services, the application retrieves data of temperature and presence sensors of rooms and temperature sensors from outside, and for example depending on the values of the data, actions can be triggered. Your application must be based on a service-oriented architecture.  

An example of a scenario can be: the data of the temperature sensors of a given room are retrieved periodically. If the outside temperature is lower than the indoor temperature and the outside temperature is between 18 and 27°, the windows should be opened automatically. We can also consider the scenario of closing doors, windows and lights if the current time is outside working hours and there is nobody. However, if there are presence activities from 22h, an alarm must be triggered.

**Required work :**  

- Design your application based on SOA architecture

- Implement the different services and services calls

- Implement a web interface for viewing the history of actions  

- Tools and technologies to be used: Java, OM2M

# Setups

To run our project please follow these steps.

## 1 - Start OM2M 

Go on the *eclipse-om2m-v1.3.0* folder and run the script *run.sh*. This script remove the *in-cse* and *mn-cse* *database* directory in order to start with a clear OM2M platform. Then it will start the *in-cse* and *mn-cse* and start a web browser at the address corresponding to the OM2M platform (here *http://localhost:8081/webpage*.

Once on this webpage, connect using the following *id* and *password*:

- login: *admin*
- password: *admin*

Finally go to the *mn-name* page.

## 2 - Create the environment

Here we are going to create the room with all the sensors/actuators on OM2M.
Open the java project called *fr.insat.om2m.initRooms* and run the *main.java*. This program will create a room call *GEI_213* and initialise 7 virtual sensors:

- Indoor temperature sensor.
- Outdoor temperature sensor.
- Window sensor (open/close).
- Door sensor (open/close).
- Light sensor (on/off).
- Presence sensor (true/false).
- Alarm (on/off).

## 3 - Run the REST services

Because we had to design our application based on SOA architecture, we created some **REST web services**. The role of theses services is to interact with the OM2M ressources thanks to **GET** and **POST**  requests, in order to retrieve or set information. (cf part 4). 

We created one service for each of the sensor describe in the previous part.

You can find these services in the *RestServicesRoom* project.

To run these services, run this project on a *Tomcat v7.0 server*. 

## 4 - Start the controller

Open  and run the java project called *RoomManagement*.
This one is in charge of the management of the room. It will do the following tasks:

- If tempExt < tempInt et 17<tempExt<28 so open the windows. Otherwise close the windows.
- Close doors, windows and switch off the lights if it's earlier than 8 am or later than 6 pm and there is nobody in the room.
- If there is someone after 22h => Turn on the alarm.

To do this, it uses the **HTTP GET** & **POST** methods to respectively retrieve information from the various sensors of the room and use the actuators depending on the situation. The **GET** and **POST** are done on the **REST services** presented in part 3.

Note that because the sensors are virtual, the actuators (to switch on/off the lights, open/close the door, etc) are represented by a change of state of the sensors.


## 5 - Use the web interface

Open the java project entitled *RoomManagementAppWeb*. You can start the project (Run on server Tomcat). Eclipse will open a web page with the login and password text input, but you access to the login page from you navigator at:

http://localhost:8080/RoomManagementAppWeb/index.html

Once on this webpage, connect using the following *id* and *password*:

- login: *admin*
- password: *admin*

You will be redirected to the management page, you can refresh the page to have all the room parameters of the room listed.

You have 3 main sections in this web interface, you can find:

### Room Parameters
You can refresh the room parameters of the selected room, and you can obtain:
- The indoor and outdoor temperature
- The door state
- The state of all the windows
- The alarm state
- The light state

### Manage the Room
Thanks to the different buttons, and the selected room, you can manage the room:
- Close/open the door
- Close/open all the windows
- Turn on/off the lights
- Measure the indoor/outdoor temperature
- Check the presence to activate or deactivate the alarm

### History of actions
To show all the previous commands made on the web interface.


