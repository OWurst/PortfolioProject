package OWurst.Investment_Simulator.Service.ThirdParty;

import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import OWurst.Investment_Simulator.Entity.Stock;

@Service
public class FlaskWrapper implements ThirdPartyAPI {
    // This class is used to wrap the Flask API
    // It will be used to make requests to the Flask API
    // The Flask API will be used to get the stock data from yfinance

    static String url = "http://127.0.0.1:5000/";

    @Override
    public Stock getStock(String ticker, Date startDate, Date endDate, String interval, int inter) {
        return null;
    }

    @Override
    public Stock searchStock(String subString) {
        return null;
    }

    @Override
    public ArrayList<Stock> getStocks(String[] tickers) {
        HttpClient client = HttpClient.newHttpClient();

        // Convert the tickers array into a JSON string
        String jsonString = new Gson().toJson(new HashMap<String, Object>() {
            {
                put("tickers", Arrays.asList(tickers));
            }
        });

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5000/StockService/getStockListInfo"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // if the response is not 200, return null
            if (response.statusCode() != 200) {
                System.out.println("Response code: " + response.statusCode() + " Response: " + response.body());
                return null;
            }

            String content = response.body();

            // Parse the JSON into a JsonObject
            JsonObject jsonObject = new Gson().fromJson(content, JsonObject.class);

            // Extract the 'stocks' array from the JsonObject
            JsonArray jsonArray = jsonObject.getAsJsonArray("stocks");

            // Convert the JsonArray into an ArrayList of Stock
            Type listType = new TypeToken<ArrayList<Stock>>() {
            }.getType();
            ArrayList<Stock> stocks = new Gson().fromJson(jsonArray, listType);

            return stocks;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public double predictStock(String ticker) {
        return 0;
    }

    @Override
    public double predictSPY() {
        return 0;
    }

    @Override
    public ArrayList<String> getAllTickers() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5000/StockService/getAllTickers"))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String content = response.body();
            // Parse the JSON into a JsonObject
            JsonObject jsonObject = new Gson().fromJson(content, JsonObject.class);

            // Extract the 'tickers' array from the JsonObject
            JsonArray jsonArray = jsonObject.getAsJsonArray("tickers");

            // Convert the JsonArray into an ArrayList
            Type listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            ArrayList<String> tickers = new Gson().fromJson(jsonArray, listType);

            return tickers;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}