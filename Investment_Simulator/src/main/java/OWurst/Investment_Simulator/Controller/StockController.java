package OWurst.Investment_Simulator.Controller;

import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import OWurst.Investment_Simulator.Constants.AddressConstants;
import OWurst.Investment_Simulator.Entity.Stock;
import OWurst.Investment_Simulator.Service.StockService;

@RestController
@CrossOrigin(origins = AddressConstants.FRONTEND_URL, allowCredentials = "true")
@RequestMapping("/core/api")
public class StockController {

    @Autowired
    StockService stockService;

    @PutMapping("/updateStockValues")
    public ResponseEntity<String> updateMyStocks(HttpRequest request) {
        return stockService.updateStocks(request);
    }

    @GetMapping("/searchForStocks")
    public ResponseEntity<List<Stock>> findStock(@RequestParam String toSearch, HttpRequest request) {
        return stockService.findStock(toSearch, request);
    }

    @PostMapping("createStockTable")
    public ResponseEntity<String> createTable(HttpRequest request) {
        return stockService.createTable(request);
    }
}
