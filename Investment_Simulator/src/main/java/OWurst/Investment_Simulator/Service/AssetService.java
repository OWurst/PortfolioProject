package OWurst.Investment_Simulator.Service;

import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;

public interface AssetService {
    public ResponseEntity<Double> getCash(HttpServletRequest request);

    public ResponseEntity<String> buyStock(String ticker, String cost, String count,
            HttpServletRequest request);
}
