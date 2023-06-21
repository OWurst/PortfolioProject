package OWurst.Investment_Simulator.Primary_Layers.DB_Layer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import OWurst.Investment_Simulator.General_Objects.Entity.User;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findOneByUsernameAndPassword(String username, String password);

    public User findByUsername(String username);

    public User findOneById(int id);
}