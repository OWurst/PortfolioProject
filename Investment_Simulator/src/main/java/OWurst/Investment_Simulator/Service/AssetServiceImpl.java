package OWurst.Investment_Simulator.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import OWurst.Investment_Simulator.Entity.Assets;
import OWurst.Investment_Simulator.Entity.User;
import OWurst.Investment_Simulator.Entity.Stock;
import OWurst.Investment_Simulator.Repository.AssetRepository;
//import OWurst.Investment_Simulator.Repository.StockSetRepository;
import OWurst.Investment_Simulator.Repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    // private StockSetRepository stockSetRepository;

    public ResponseEntity<Double> getCash(HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute("USER_ID");
        Assets assets = assetRepository.findOneById(userId);

        return ResponseEntity.ok().body(assets.getCash());
    }

    public ResponseEntity<Object> getStocks(HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute("USER_ID");
        Map<String, Stock> stockset = assetRepository.findOneById(userId).getStockSet();
        Object stockList = null;

        try {
            stockList = stocksetToList(stockset);
        } catch (Exception e) {
            System.out.println("\n\nstupid error\n\n");
        }

        return ResponseEntity.ok().body(stockList);
    }

    public ResponseEntity<String> buyStock(String ticker, String company, String cost, String count,
            HttpServletRequest request) {
        Assets assets = getAssetsFromSession(request);
        if (canAffordPurchase(assets, Double.parseDouble(cost) * Double.parseDouble(count))) {
            addStockToUser(assets, company, ticker, cost, count);
            decrementUserCash(assets, Double.parseDouble(cost) * Double.parseDouble(count));
        } else {
            String msg = "Purchase can not be made: user cannot afford " + count + " shares of " + ticker + " at "
                    + cost + " per share";
            return ResponseEntity.badRequest().body(msg);
        }
        return ResponseEntity.ok().body("Purchase Successful");
    }

    public ResponseEntity<String> sellStock(String ticker, String cost, String count,
            HttpServletRequest request) {
        Assets assets = getAssetsFromSession(request);
        if (haveAssetsToSell(assets, ticker, Long.parseLong(count))) {
            removeStockFromUser(assets, ticker, Long.parseLong(count));
            incrementUserCash(assets, Double.parseDouble(cost) * Double.parseDouble(count));
        } else {
            String msg = "Sale can not be made: user does not have " + count + " shares of " + ticker;
            return ResponseEntity.badRequest().body(msg);
        }
        return ResponseEntity.ok().body("Sale Successful");
    }

    private void addStockToUser(Assets assets, String company, String ticker, String cost, String count) {
        Stock stock = assets.getStock(ticker);
        if (stock == null) {
            stock = new Stock(ticker, company, Double.parseDouble(cost), Long.parseLong(count));
        } else {
            stock.incrementStockCount(Long.parseLong(count));
            stock.setStockPrice(Double.parseDouble(count));
        }
        assets.addStock(ticker, stock);
        assetRepository.save(assets);
    }

    private void removeStockFromUser(Assets assets, String ticker, long count) {
        Stock stock = assets.getStock(ticker);
        if (stock.getCount() == count) {
            assets.getStockSet().remove(ticker);
        } else {
            stock.decrementStockCount(count);
            assets.getStockSet().put(ticker, stock);
        }
        assetRepository.save(assets);
    }

    private Object stocksetToList(Map<String, Stock> stockset) throws JsonProcessingException {
        Map<String, Object> json = new HashMap<String, Object>();
        List<Object> stockList = new ArrayList<Object>();
        ObjectMapper mapper = new ObjectMapper();
        for (String key : stockset.keySet()) {
            stockList.add(0, stockset.get(key));
        }

        json.put("stocks", mapper.writeValueAsString(stockList));
        return json;
    }

    private Assets getAssetsFromSession(HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute("USER_ID");
        User user = userRepository.findOneById(userId);

        return user.getAssets();
    }

    private boolean canAffordPurchase(Assets assets, double totalCost) {
        if (assets.getCash() < totalCost)
            return false;
        else
            return true;
    }

    private boolean haveAssetsToSell(Assets assets, String ticker, long count) {
        Stock toSell = assets.getStockSet().get(ticker);
        if (toSell == null)
            return false;

        if (toSell.getCount() >= count)
            return true;
        else
            return false;
    }

    private void decrementUserCash(Assets assets, double totalCost) {
        assets.decCash(totalCost);
        assetRepository.save(assets);
    }

    private void incrementUserCash(Assets assets, double saleWorth) {
        assets.incCash(saleWorth);
        assetRepository.save(assets);
    }
}
