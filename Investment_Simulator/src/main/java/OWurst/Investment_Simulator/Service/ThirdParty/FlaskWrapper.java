package OWurst.Investment_Simulator.Service.ThirdParty;

import java.util.Date;

import OWurst.Investment_Simulator.Entity.Stock;

public class FlaskWrapper implements ThirdPartyAPI {
    // This class is used to wrap the Flask API
    // It will be used to make requests to the Flask API
    // The Flask API will be used to get the stock data from yfinance

    @Override
    public Stock getStock(String ticker, Date startDate, Date endDate, String interval, int inter) {
        return null;
    }

    @Override
    public Stock searchStock(String subString) {
        return null;
    }

    @Override
    public Stock getStocks(String[] tickers) {
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
}