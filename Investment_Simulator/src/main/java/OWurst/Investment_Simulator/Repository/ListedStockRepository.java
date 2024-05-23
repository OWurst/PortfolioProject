package OWurst.Investment_Simulator.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import OWurst.Investment_Simulator.Entity.Stock;

@EnableJpaRepositories
public interface ListedStockRepository extends JpaRepository<Stock, Integer> {
    public Stock findOneByTicker(String symbol);

    public Stock findOneByCompanyName(String companyName);

    public List<Stock> findByCompanyNameContaining(String companyName);

    public List<Stock> findByTickerContaining(String ticker);

    public List<Stock> findByIndustry(String industry);

    public List<Stock> findByIndustryContaining(String industry);
}