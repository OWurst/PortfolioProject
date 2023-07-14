package OWurst.Investment_Simulator.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import OWurst.Investment_Simulator.Constants.AddressConstants;
import OWurst.Investment_Simulator.Entity.Stock;
import OWurst.Investment_Simulator.Service.AssetService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = AddressConstants.FRONTEND_URL, allowCredentials = "true")
@RequestMapping("/assets")
public class AssetController {
    @Autowired
    AssetService assetService;

    @GetMapping("/userStocks")
    public ResponseEntity<List<Stock>> getAssets(HttpServletRequest request) {
        return null;
    }

    @GetMapping("/userCash")
    public ResponseEntity<Double> getCash(@RequestParam String id, HttpServletRequest request) {
        return assetService.getCash(id, request);
    }

    @PostMapping("/buyStock")
    public ResponseEntity<String> buyStock(@RequestParam String id, @RequestParam String ticker,
            @RequestParam String cost, @RequestParam String count, HttpServletRequest request) {
        return assetService.buyStock(id, ticker, cost, count, request);
    }

    @PostMapping("/sellStock")
    public ResponseEntity<String> sellStock(HttpServletRequest request) {
        return null;
    }
}
