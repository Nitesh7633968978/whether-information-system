import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherApp {

    private static final String API_KEY = "your_api_key"; // Replace with your OpenWeatherMap API key
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=%s";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Weather Information Application");
        System.out.print("Enter the name of the city: ");
        String city = scanner.nextLine();

        try {
            String response = getWeatherData(city);
            if (response != null) {
                parseAndDisplayWeatherData(response);
            } else {
                System.out.println("Could not fetch weather data. Please try again later.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static String getWeatherData(String city) throws Exception {
        String urlString = String.format(API_URL, city, API_KEY);
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            System.out.println("Error: Unable to fetch data. HTTP response code: " + responseCode);
            return null;
        }
    }

    private static void parseAndDisplayWeatherData(String response) {
        try {
            /*org.*/jsonJSONObject jsonObject = new /*org.*/jsonJSONObject(response);
                 //Scanner sc = new Scanner(System.in);
            String cityName = jsonObject.getString("name");
            org.json.JSONObject main = jsonObject.getJSONObject("main");
            double temperature = main.getDouble("temp");
            int humidity = main.getInt("humidity");

            org.json.JSONArray weatherArray = jsonObject.getJSONArray("weather");
            String description = weatherArray.getJSONObject(0).getString("description");

            System.out.println("\nWeather Information for " + cityName + ":");
            System.out.println("Temperature: " + temperature + "°C");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Description: " + description);

        } catch (org.json.JSONException e) {
            System.out.println("Error parsing weather data: " + e.getMessage());
        }
    }
}




