package OWurst.Investment_Simulator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import OWurst.Investment_Simulator.Repository.APIStockRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    APIStockRepository stockRepository;

    public ResponseEntity<String> updateStocks(HttpServletRequest request) {
        try {
            getUserId(request);
        } catch (Exception e) {
            return unverifiedRequester();
        }
        return null;
    }

    public ResponseEntity<String> findStock(String toSearch, HttpServletRequest request) { // list?? this is for search
        try {
            getUserId(request);
        } catch (Exception e) {
            return unverifiedRequester();
        }
        return null;
    }

    public ResponseEntity<String> createTable(HttpServletRequest request) {
        try {
            getUserId(request);
        } catch (Exception e) {
            return unverifiedRequester();
        }
        return null;
    }

    private int getUserId(HttpServletRequest request) throws Exception {
        return (int) request.getSession().getAttribute("USER_ID");
    }

    private ResponseEntity<String> unverifiedRequester() {
        String msg = "Error: User Not Verified";
        return ResponseEntity.badRequest().body(msg);
    }
}
