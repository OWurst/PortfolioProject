package OWurst.Investment_Simulator.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import OWurst.Investment_Simulator.Entity.Assets;

@EnableJpaRepositories
public interface AssetRepository extends JpaRepository<Assets, Integer> {
    public Assets findOneById(int id);
}