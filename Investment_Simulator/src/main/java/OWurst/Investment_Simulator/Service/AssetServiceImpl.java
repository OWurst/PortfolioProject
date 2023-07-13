package OWurst.Investment_Simulator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import OWurst.Investment_Simulator.Entity.Assets;
import OWurst.Investment_Simulator.Repository.AssetRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public double getCash(String id, HttpServletRequest request) {
        int userId = Integer.parseInt(id);
        Assets assets = assetRepository.findOneById(userId);
        return assets.getCash();
    }
}
