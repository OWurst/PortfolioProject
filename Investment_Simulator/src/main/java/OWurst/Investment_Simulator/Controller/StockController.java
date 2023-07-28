package OWurst.Investment_Simulator.Controller;

import java.net.http.HttpRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import OWurst.Investment_Simulator.Constants.AddressConstants;
import OWurst.Investment_Simulator.DTO.StockListDTO;

@RestController
@CrossOrigin(origins = AddressConstants.FRONTEND_URL, allowCredentials = "true")
@RequestMapping("/core/api")
public class StockController {
    @GetMapping("/updateStockValues")
    public String updateMyStocks(StockListDTO stockListDTO, HttpRequest request) {
        return "update stocks";
    }

    @GetMapping("/searchForStocks")
    public String findStock(@RequestParam String toSearch, HttpRequest request) {
        return "get stock";
    }
}
