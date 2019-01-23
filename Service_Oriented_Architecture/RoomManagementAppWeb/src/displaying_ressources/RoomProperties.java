package displaying_ressources;

import java.util.ArrayList;

public class RoomProperties {
	private long IndoorTemp;
	private long OutdoorTemp;
	private String DoorState;
	private String WindowState;
	private String AlarmState;
	private String LightState;
	private int roomId;
	
	//Class constructor
	public RoomProperties(int id) {
		// Initialize all the variables to default values
		this.roomId = id;
		this.IndoorTemp = -1;
		this.OutdoorTemp = -1;
		this.DoorState = "Opened";
		this.WindowState = "Closed";
		this.AlarmState = "Desactivated";
		this.LightState = "Off";
	}

	//Getters and Setters of the RoomProperties class
	public int getRoomId() {
		return roomId;
	}

	public String getLightState() {
		return LightState;
	}

	public void setLightState(String lightState) {
		LightState = lightState;
	}

	public String getAlarmState() {
		return AlarmState;
	}

	public void setAlarmState(String alarmState) {
		AlarmState = alarmState;
	}

	public String getDoorState() {
		return DoorState;
	}

	public void setDoorState(String doorState) {
		DoorState = doorState;
	}

	public String getWindowState() {
		return WindowState;
	}

	public void setWindowState(String windowState) {
		WindowState = windowState;
	}

	public long getIndoorTemp() {
		return IndoorTemp;
	}

	public void setIndoorTemp(long indoorTemp) {
		IndoorTemp = indoorTemp;
	}

	public long getOutdoorTemp() {
		return OutdoorTemp;
	}

	public void setOutdoorTemp(long outdoorTemp) {
		OutdoorTemp = outdoorTemp;
	}
	
	
}
