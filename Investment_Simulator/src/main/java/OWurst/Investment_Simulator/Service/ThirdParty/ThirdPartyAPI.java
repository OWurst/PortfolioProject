package OWurst.Investment_Simulator.Service.ThirdParty;

import OWurst.Investment_Simulator.DTO.StockDTO;
import OWurst.Investment_Simulator.Entity.Stock;
import java.util.Date;
import java.util.ArrayList;

// This interface is used to define the methods that the third party API will have to implement
// I've chosen this structure so that I will be able to change the Wrapper class without changing the rest of the code
// allowing me to easily switch between different APIs
public interface ThirdPartyAPI {
    public Stock getStock(String ticker, Date startDate, Date endDate, String interval, int intervalCnt);

    public Stock searchStock(String subString);

    public ArrayList<StockDTO> getStocks(String[] tickers);

    public double predictStock(String ticker);

    public double predictSPY();

    public ArrayList<String> getAllTickers();
}
