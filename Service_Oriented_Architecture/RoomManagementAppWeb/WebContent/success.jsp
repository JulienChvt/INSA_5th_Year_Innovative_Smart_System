

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Rooms</title>
    </head>
    <body>
        <div style="text-align:center">
            <img src="images/logo-insa.jpg" alt="INSA Logo" title="INSA Logo">

        </div>
        
        
<!-- Show all the room parameters and allow you to refresh them through a get request -->
<h1>Room Parameters</h1>
<table>
    <!-- Gather all the parameters thanks to the class RoomProperties -->
<tr>
    <td>Indoor temperature:</td>
    <td> ${RoomProper.getIndoorTemp()} </td>
</tr>
<tr>
    <td>Outdoor temperature:</td>
    <td> ${RoomProper.getOutdoorTemp()} </td>
</tr>
<tr>
    <td>Door State:</td>
    <td> ${RoomProper.getDoorState()} </td>
</tr>
<tr>
    <td>Window State:</td>
    <td> ${RoomProper.getWindowState()} </td>
</tr>
<tr>
    <td>Alarm State:</td>
    <td> ${RoomProper.getAlarmState()} </td>
</tr>
<tr>
    <td>Light State:</td>
    <td> ${RoomProper.getLightState()} </td>
</tr>
<tr>
    <form method="get" action="RefreshProperties">
        <td>Room: </td>
        <td>
            <select name="RoomNumber">
                <div> ${ListRoom} </div>
                    </select>
                </td>
                <td>
                    <!-- Button to refresh all the parameters of the chosen room -->
                    <input type="submit" name="action" value="Refresh">

                </td>
            </form>
        </tr>
    </table>
    
    
<!-- Manage all the resources of the room through post requests -->
<h1>Manage Room</h1>
<form method="post" action="ManageRoom">
	<table>
        <tr>
            <td>Room: </td>
            <td>
                <!-- Drop-down list to show all the available doors -->
             <select name="RoomNumber">
                 <div> ${ListRoom} </div>
                     </select>
                 </td>
             </tr>
             <tr>
                 <td>Doors: </td>
                 <td>
                     <!-- Button to close the door with the chosen number -->
                     <input type="submit" name="action" value="Close the door">

                 </td>
                 <td>
                     <!-- Button to open the door with the chosen number -->
                     <input type="submit" name="action" value="Open the door">

                 </td>
             </tr>
             <tr>
                 <td>Windows: </td>
                 <td>
                     <!-- Button to close the window with the chosen number -->
                     <input type="submit" name="action" value="Close the Window">

                 </td>
                 <td>
                     <!-- Button to open the window with the chosen number -->
                     <input type="submit" name="action" value="Open the Window">

                 </td>
             </tr>
             <tr>
                 <td>Light: </td>
                 <td>
                     <!-- Button to allow you to turn on the light -->
                     <input type="submit" name="action" value="Turn on light">

                 </td>
                 <td>
                     <!-- Button to allow you to turn off the light -->
                     <input type="submit" name="action" value="Turn off light">

                 </td>
             </tr>
             <tr>
                 <td>Temperature: </td>
                 <td>
                     <!-- Button to get the Indoor temperature -->
                     <input type="submit" name="action" value="Measure Indoor Temperature">

                 </td>
                 <td>
                     <!-- Button to get the Outdoor temperature -->
                     <input type="submit" name="action" value="Measure Outdoor Temperature">

                 </td>
             </tr>
     	</table>
     </form>
     
     
<!-- Show all the previous commands -->
<h1>History of actions</h1>
	<div> ${history} 
	    </div>
</body>
<!-- liste déroulante: http://www.lehtml.com/htmlplus/fmliste.htm -->
<!-- HTML code ascii: https://www.ascii.cl/htmlcodes.htm -->

 </html>