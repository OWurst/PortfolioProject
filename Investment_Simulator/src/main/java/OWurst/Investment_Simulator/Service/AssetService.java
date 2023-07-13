package OWurst.Investment_Simulator.Service;

import jakarta.servlet.http.HttpServletRequest;

public interface AssetService {
    public double getCash(String id, HttpServletRequest request);
}
