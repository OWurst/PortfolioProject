package OWurst.Investment_Simulator.Service;

import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;

public interface StockService {
    public ResponseEntity<String> updateStocks(HttpServletRequest request);

    public ResponseEntity<String> findStock(String toSearch, HttpServletRequest request);

    public ResponseEntity<String> createTable(HttpServletRequest request);
}
