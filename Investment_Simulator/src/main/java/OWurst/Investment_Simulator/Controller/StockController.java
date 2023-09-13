package OWurst.Investment_Simulator.Controller;

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
import OWurst.Investment_Simulator.Service.StockService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = AddressConstants.FRONTEND_URL, allowCredentials = "true")
@RequestMapping("/core/api")
public class StockController {

    @Autowired
    StockService stockService;

    @PutMapping("/updateStockValues")
    public ResponseEntity<String> updateMyStocks(HttpServletRequest request) {
        return stockService.updateStocks(request);
    }

    @GetMapping("/searchStock")
    public ResponseEntity<String> stockSearch(@RequestParam String toSearch, HttpServletRequest request) {
        return stockService.getStock(toSearch, request);
    }

    @GetMapping("/getStock")
    public ResponseEntity<String> getStock(@RequestParam String toSearch, HttpServletRequest request) {
        return stockService.getStock(toSearch, request);
    }

    @PostMapping("createStockTable")
    public ResponseEntity<String> createTable(HttpServletRequest request) {
        return stockService.createTable(request);
    }
}
