package OWurst.Investment_Simulator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import OWurst.Investment_Simulator.Constants.AddressConstants;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = AddressConstants.FRONTEND_URL, allowCredentials = "true")
@RequestMapping("/api")
public class StockController {
    @GetMapping("/hi")
    public String hi() {
        return "just farted";
    }
}
