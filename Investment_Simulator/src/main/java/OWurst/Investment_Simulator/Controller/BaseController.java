package OWurst.Investment_Simulator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import OWurst.Investment_Simulator.Service.StockService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class BaseController {
    @Autowired
    StockService stockService;

    @GetMapping("/core/spring")
    public ResponseEntity<String> Howdy() {
        return new ResponseEntity<String>("{\"msg\":\"Hello From Spring\"}", null, 200);
    }

    @PostMapping("/core/createStockTable")
    public ResponseEntity<String> createTable(HttpServletRequest request) {
        // just return hello 200 for now
        // return new ResponseEntity<String>("{\"msg\":\"Hello From Spring\"}", null,
        // 200);

        return stockService.createTable(request);
    }
}
