package OWurst.Investment_Simulator.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import OWurst.Investment_Simulator.Entity.Stock;

@EnableJpaRepositories
public interface StockSetRepository extends JpaRepository<Stock, Integer> {

}