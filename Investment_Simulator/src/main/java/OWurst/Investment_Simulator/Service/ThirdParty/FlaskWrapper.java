package OWurst.Investment_Simulator.Service.ThirdParty;

import java.util.Date;

import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
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
        return null;
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