package Part2;
import org.jibble.pircbot.*;
import Part1.WeatherAPI;
import Part1.KollywoodAPI;


public class bot extends PircBot {

	//Constructor
	public bot() {
		this.setName("NewBot");	
		}
	
	public void onMessage(String channel, String sender, String login, String hostname, String message) {

		if(message.contains("weather")) {
			WeatherAPI weather = new WeatherAPI();
		}
		
		if(message.contains("Hello")) { 
			sendMessage("#pircbot", "Yo " + sender + "! ");
		}
		
		if(message.contains("kollywood")) {
			KollywoodAPI kollywood = new KollywoodAPI();
		}
		
		if (message.equalsIgnoreCase("time")) {
			
			String time = new java.util.Date().toString();
			sendMessage(channel, sender + ": The time is now " + time);

		}

	}
	

}
