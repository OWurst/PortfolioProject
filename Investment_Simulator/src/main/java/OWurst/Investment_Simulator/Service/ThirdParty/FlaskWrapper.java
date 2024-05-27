package OWurst.Investment_Simulator.Service.ThirdParty;

import java.util.Date;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

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
        // make a request to the Flask API to get all the tickers, endpoint:
        // /all_tickers
        try {
            URL url = new URL("http://localhost:5000/StockService/getAllTickers"); // replace with your Flask API URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            // close connections
            in.close();
            conn.disconnect();
            // convert JSON array to ArrayList
            // System.out.println(content.toString());

            // Parse the JSON into a JsonObject
            JsonObject jsonObject = new Gson().fromJson(content.toString(), JsonObject.class);

            // Extract the 'tickers' array from the JsonObject
            JsonArray jsonArray = jsonObject.getAsJsonArray("tickers");

            // Convert the JsonArray into an ArrayList
            Type listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            ArrayList<String> tickers = new Gson().fromJson(jsonArray, listType);

            return tickers;
        } catch (Exception e) {
            System.out.println("Error in getAllTickers");
            e.printStackTrace();
            return null;
        }
    }
}