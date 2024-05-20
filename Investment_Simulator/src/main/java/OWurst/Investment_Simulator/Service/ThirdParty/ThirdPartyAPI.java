package OWurst.Investment_Simulator.Service.ThirdParty;

import org.springframework.stereotype.Service;

import OWurst.Investment_Simulator.Entity.Stock;

// This interface is used to define the methods that the third party API will have to implement
// I've chosen this structure so that I will be able to change the Wrapper class without changing the rest of the code
// allowing me to easily switch between different APIs
@Service
public interface ThirdPartyAPI {
    public Stock getStock(String ticker);

    public Stock searchStock(String subString);

    public Stock getStocks(String[] tickers);

    public double predictStock(String ticker);

    public double predictSPY();
}
