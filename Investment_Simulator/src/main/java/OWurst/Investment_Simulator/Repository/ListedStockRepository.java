package OWurst.Investment_Simulator.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import OWurst.Investment_Simulator.Entity.Stock;

@EnableJpaRepositories
public interface ListedStockRepository extends JpaRepository<Stock, Integer> {
    public Stock findOneByTicker(String symbol);

    public List<Stock> findByCompanyContaining(String companyName);

    public List<Stock> findByTickerContaining(String ticker);

    public List<Stock> findBySectorContaining(String sector);

    public Stock findOneByStockId(int stockId);
}