package OWurst.Investment_Simulator.Service;

import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.http.ResponseEntity;

import OWurst.Investment_Simulator.Entity.Stock;

public interface StockService {
    public ResponseEntity<String> updateStocks(HttpRequest request);

    public ResponseEntity<List<Stock>> findStock(String toSearch, HttpRequest request);

    public ResponseEntity<String> createTable(HttpRequest request);
}
