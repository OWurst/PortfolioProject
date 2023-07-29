package OWurst.Investment_Simulator.Service;

import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.http.ResponseEntity;

import OWurst.Investment_Simulator.Entity.Stock;

public class StockServiceImpl implements StockService {
    public ResponseEntity<String> updateStocks(HttpRequest request) {
        return null;
    }

    public ResponseEntity<List<Stock>> findStock(String toSearch, HttpRequest request) {
        return null;
    }

    public ResponseEntity<String> createTable(HttpRequest request) {
        return null;
    }
}
