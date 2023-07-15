package OWurst.Investment_Simulator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import OWurst.Investment_Simulator.Controller.StockController;
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

    public ResponseEntity<Double> getCash(String id, HttpServletRequest request) {
        int userId = Integer.parseInt(id);
        Assets assets = assetRepository.findOneById(userId);

        return ResponseEntity.ok().body(assets.getCash());
    }

    public ResponseEntity<String> buyStock(String id, String ticker, String cost, String count,
            HttpServletRequest request) {
        int userId = Integer.parseInt(id);

        User user = userRepository.findOneById(userId);
        Assets assets = user.getAssets();

        Stock stock = new Stock(ticker, "balls", Double.parseDouble(cost), Long.parseLong(count));

        //System.out.println("\n\nHi\n\n");
        //assets.addStock(stock);
        stockSetRepository.save(stock); /// messy, seems wrong, but maybe this is needed for creation
        //System.out.println("\n\nHello\n\n");

        return null;
    }
}
