package OWurst.Investment_Simulator.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import OWurst.Investment_Simulator.Repository.ListedStockRepository;
import OWurst.Investment_Simulator.Service.ThirdParty.ThirdPartyAPI;
import OWurst.Investment_Simulator.DTO.StockDTO;
import OWurst.Investment_Simulator.Entity.Stock;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    ListedStockRepository stockRepository;

    @Autowired
    ThirdPartyAPI thirdPartyAPI;

    public ResponseEntity<String> updateStocks(HttpServletRequest request) {
        try {
            // int id = getUserId(request);
            // Assets assets = assetRepository.findOneById(id);
        } catch (Exception e) {
            return unverifiedRequester();
        }
        return null;
    }

    public ResponseEntity<String> searchStock(String toSearch, HttpServletRequest request) { // list?? for search
        try {
            getUserId(request);
        } catch (Exception e) {
            return unverifiedRequester();
        }
        return null;
    }

    public ResponseEntity<String> getStock(String toSearch, HttpServletRequest request) { // list?? for search
        try {
            getUserId(request);
        } catch (Exception e) {
            return unverifiedRequester();
        }
        return null;
    }

    // do this first
    // look into a stream for this, is it possible to get stocks and their values
    // via an interface and work on adding them to the database faster than a simple
    // do all in wrapper interface, then all in this??
    // possibly another server...Quarkus??
    public ResponseEntity<String> createTable(HttpServletRequest request) {
        // try {
        // getUserId(request);
        // } catch (Exception e) {
        // return unverifiedRequester();
        // }
        try {

            ArrayList<String> allStocks = thirdPartyAPI.getAllTickers();
            // convert allStocks to String array
            String[] allStocksArray = new String[allStocks.size()];
            allStocksArray = allStocks.toArray(allStocksArray);

            System.out.println("First stock: ");
            System.out.println(allStocksArray[0]);
            // get all stocks from the API
            ArrayList<StockDTO> stocks = thirdPartyAPI.getStocks(allStocksArray);
            for (StockDTO stock : stocks) {
                Stock newStock = new Stock(stock);
                stockRepository.save(newStock);
            }

            return ResponseEntity.ok().body("Table Created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: Table Not Created");
        }
    }

    private int getUserId(HttpServletRequest request) throws Exception {
        return (int) request.getSession().getAttribute("USER_ID");
    }

    private ResponseEntity<String> unverifiedRequester() {
        String msg = "Error: User Not Verified";
        return ResponseEntity.badRequest().body(msg);
    }
}
