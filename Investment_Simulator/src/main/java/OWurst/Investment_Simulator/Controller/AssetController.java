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
import OWurst.Investment_Simulator.Service.AssetService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = AddressConstants.FRONTEND_URL, allowCredentials = "true")
@RequestMapping("/assets")
public class AssetController {
    @Autowired
    AssetService assetService;

    @GetMapping("/userStocks")
    public ResponseEntity<Object> getAssets(HttpServletRequest request) {
        return assetService.getStocks(request);
    }

    @GetMapping("/userCash")
    public ResponseEntity<Double> getCash(HttpServletRequest request) {
        return assetService.getCash(request);
    }

    @PostMapping("/buyStock")
    public ResponseEntity<String> buyStock(@RequestParam String ticker, @RequestParam String company,
            @RequestParam String cost, @RequestParam String count, HttpServletRequest request) {
        return assetService.buyStock(ticker, company, cost, count, request);
    }

    @PostMapping("/sellStock")
    public ResponseEntity<String> sellStock(@RequestParam String ticker,
            @RequestParam String cost, @RequestParam String count, HttpServletRequest request) {
        return assetService.sellStock(ticker, cost, count, request);
    }
}
