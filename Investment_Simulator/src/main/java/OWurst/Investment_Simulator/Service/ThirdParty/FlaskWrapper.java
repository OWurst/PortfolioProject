package OWurst.Investment_Simulator.Service.ThirdParty;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.json.JSONObject;
import org.json.JSONArray;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;

import OWurst.Investment_Simulator.Entity.Stock;
import OWurst.Investment_Simulator.DTO.StockDTO;

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
    public ArrayList<StockDTO> getStocks(String[] tickers) {
        HttpClient client = HttpClient.newHttpClient();

        // Convert the tickers array into a JSON string
        JSONObject jsonObjectInput = new JSONObject();
        jsonObjectInput.put("tickers", new JSONArray(Arrays.asList(tickers)));
        String jsonString = jsonObjectInput.toString();

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
            JSONObject jsonObject = new JSONObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray("stocks");

            ArrayList<StockDTO> stocks = new ArrayList<StockDTO>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject stockObject = jsonArray.getJSONObject(i);
                StockDTO stock = new StockDTO(
                        stockObject.getString("ticker"),
                        stockObject.getString("company"),
                        stockObject.getString("sector"),
                        stockObject.getString("industry"),
                        stockObject.getDouble("price"));
                stocks.add(stock);
            }
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
            JSONObject jsonObject = new JSONObject(content);

            JSONArray jsonArray = jsonObject.getJSONArray("tickers");

            ArrayList<String> tickers = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                tickers.add(jsonArray.getString(i));
            }
            return tickers;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}