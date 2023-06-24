package OWurst.Investment_Simulator.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import OWurst.Investment_Simulator.Entity.User;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findOneByUsernameAndPassword(String username, String password);

    public User findByUsername(String username);

    public User findOneById(int id);
}