package OWurst.Investment_Simulator.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import OWurst.Investment_Simulator.Entity.Stock;

import jakarta.servlet.http.HttpServletRequest;

public interface StockService {
    public ResponseEntity<String> updateStocks(HttpServletRequest request);

    public ResponseEntity<List<Stock>> findStock(String toSearch, HttpServletRequest request);

    public ResponseEntity<String> createTable(HttpServletRequest request);
}
