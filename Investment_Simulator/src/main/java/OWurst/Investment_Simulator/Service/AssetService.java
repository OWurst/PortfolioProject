package OWurst.Investment_Simulator.Service;

import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;

public interface AssetService {
    public ResponseEntity<Double> getCash(String id, HttpServletRequest request);

    public ResponseEntity<String> buyStock(String id, String ticker, String cost, String count,
            HttpServletRequest request);
}
