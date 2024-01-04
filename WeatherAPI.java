package Part1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.*;


public class WeatherAPI {
	
	public static void main (String[] args) throws IOException {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Which city would you like to know the weather for?");
		String location = input.next();
		
		String apiEndpoint = "api.openweathermap.org";
		StringBuilder requestB = new StringBuilder(apiEndpoint);
		requestB.append(location);
		
		URL url = new URL(requestB.toString());
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		Map<String, String> parameters = new HashMap<>();
		parameters.put("param1", "val");

		con.setDoOutput(true);
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
		out.flush();
		out.close();
		
		
		con.setRequestProperty("Content-Type", "application/json");
		String contentType = con.getHeaderField("Content-Type");
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		
		int status = con.getResponseCode(); //GET request
		BufferedReader BufferReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine = null;
			StringBuffer result = new StringBuffer(); //To store the connection data with thread safety
			while ((inputLine = BufferReader.readLine()) != null) {
				    result.append(inputLine);
				}
			
		String resultString = result.toString();
		BufferReader.close();
		con.disconnect();
		input.close();
		
		double temperature = getTemperature(resultString);

        // Print the temperature
        System.out.println("Temperature: " + temperature);
	}




	public static double getTemperature(String json) {
		JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();

        // Extract the temperature from the JSON object
        if (jsonObject.has("temp")) {
            return jsonObject.get("temp").getAsDouble();
        } else {
            // Handle the case where the "temperature" field is not present
            throw new IllegalArgumentException("JSON does not contain a temperature field");
        }

}
}

class ParameterStringBuilder {
    public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
          result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
          result.append("=");
          result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
          result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
    }
    
}

