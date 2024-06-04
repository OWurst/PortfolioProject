package OWurst.Investment_Simulator.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import OWurst.Investment_Simulator.Entity.PortfolioValueEntry;

@EnableJpaRepositories
public interface PorfolioValueRepository extends JpaRepository<PortfolioValueEntry, Integer> {
    public List<PortfolioValueEntry> findByUserId(int userId);

    public PortfolioValueEntry findTopByOrderByValueDesc();
}