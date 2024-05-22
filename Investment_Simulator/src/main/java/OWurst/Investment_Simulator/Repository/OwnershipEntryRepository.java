package OWurst.Investment_Simulator.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import OWurst.Investment_Simulator.Entity.OwnershipEntry;

@EnableJpaRepositories
public interface OwnershipEntryRepository extends JpaRepository<OwnershipEntry, Integer> {
    public List<OwnershipEntry> findByUserId(int userId);
}