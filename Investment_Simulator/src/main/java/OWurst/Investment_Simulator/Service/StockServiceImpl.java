package OWurst.Investment_Simulator.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import OWurst.Investment_Simulator.Entity.Stock;
import OWurst.Investment_Simulator.Repository.APIStockRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    APIStockRepository stockRepository;

    public ResponseEntity<String> updateStocks(HttpServletRequest request) {
        return null;
    }

    public ResponseEntity<List<Stock>> findStock(String toSearch, HttpServletRequest request) {
        return null;
    }

    public ResponseEntity<String> createTable(HttpServletRequest request) {
        try {
            getUserId(request);
        } catch (Exception e) {
            String msg = "Error: User Not Verified";
            return ResponseEntity.badRequest().body(msg);
        }
        return null;
    }

    private int getUserId(HttpServletRequest request) throws Exception {
        return (int) request.getSession().getAttribute("USER_ID");
    }
}
