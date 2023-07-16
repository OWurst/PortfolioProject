package OWurst.Investment_Simulator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import OWurst.Investment_Simulator.Entity.Assets;
import OWurst.Investment_Simulator.Entity.User;
import OWurst.Investment_Simulator.Entity.Stock;
import OWurst.Investment_Simulator.Repository.AssetRepository;
import OWurst.Investment_Simulator.Repository.StockSetRepository;
import OWurst.Investment_Simulator.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    StockSetRepository stockSetRepository;

    public ResponseEntity<Double> getCash(HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute("USER_ID");
        Assets assets = assetRepository.findOneById(userId);

        return ResponseEntity.ok().body(assets.getCash());
    }

    public ResponseEntity<String> buyStock(String ticker, String cost, String count,
            HttpServletRequest request) {
        Assets assets = getAssetsFromSession(request);
        if (canAffordPurchase(assets, Double.parseDouble(cost) * Double.parseDouble(count))) {
            addStockToUser(assets, ticker, cost, count);
            decrementUserCash(assets, Double.parseDouble(cost) * Double.parseDouble(count));
        } else {
            String msg = "Purchase can not be made: user cannot afford " + count + " shares of " + ticker + " at "
                    + cost + " per share";
            return ResponseEntity.badRequest().body(msg);
        }
        return ResponseEntity.ok().body("Purchase Successful");
    }

    private void addStockToUser(Assets assets, String ticker, String cost, String count) {
        Stock stock = assets.getStock(ticker);
        if (stock == null) {
            stock = new Stock(ticker, "balls", Double.parseDouble(cost), Long.parseLong(count));
        } else {
            stock.incrementStockCount(Long.parseLong(count));
            stock.setStockPrice(Double.parseDouble(count));
        }
        assets.addStock(ticker, stock);
        assetRepository.save(assets);
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

    private void decrementUserCash(Assets assets, double totalCost) {
        assets.decCash(totalCost);
        assetRepository.save(assets);
    }
}
