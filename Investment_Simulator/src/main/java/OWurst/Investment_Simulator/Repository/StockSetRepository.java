package OWurst.Investment_Simulator.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import OWurst.Investment_Simulator.Entity.Stock;

@EnableJpaRepositories
public interface StockSetRepository extends JpaRepository<Stock, Integer> {
    public ArrayList<Stock> findById(int id);

    public Stock findByTicker(String ticker);
}