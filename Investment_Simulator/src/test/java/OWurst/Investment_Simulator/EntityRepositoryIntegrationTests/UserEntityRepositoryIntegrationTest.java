package OWurst.Investment_Simulator.EntityRepositoryIntegrationTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import OWurst.Investment_Simulator.Entity.User;
import OWurst.Investment_Simulator.Repository.UserRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
public class UserEntityRepositoryIntegrationTest {
    @Autowired
    UserRepository userRepository;

    User user1;
    User user2;

    @Before
    public void setUp() {
        user1 = new User("John", "Doe", "johndoe", "password", "jd@gmail.com");
        userRepository.save(user1);

        user2 = new User("Jane", "Doe", "janedoe", "password", "jd2@gmail.com");
        userRepository.save(user2);
    }

    @Test
    public void testFindUserByUsername() {
        User user = userRepository.findByUsername("johndoe");
        assertEquals(user1, user);
    }

    @Test
    public void testFindUserByUsernameAndPassword() {
        User user = userRepository.findOneByUsernameAndPassword("johndoe", "password");
        assertEquals(user1, user);
    }

    @Test
    public void testFindUserByUserId() {
        User user = userRepository.findOneByUserId(user1.getUserId());
        assertEquals(user1, user);
    }

    @Test
    public void testFindUserByUserIdNotFound() {
        User user = userRepository.findOneByUserId(100);
        assertNull(user);
    }

    @Test
    public void testFindUserByUsernameNotFound() {
        User user = userRepository.findByUsername("notfound");
        assertNull(user);
    }

    @Test
    public void testFindUserByUsernameAndPasswordNotFound() {
        User user = userRepository.findOneByUsernameAndPassword("johndoe", "notfound");
        assertNull(user);
    }

    @Test
    public void testFindUserByUsernameAndPasswordNotFound2() {
        User user = userRepository.findOneByUsernameAndPassword("notfound", "password");
        assertNull(user);
    }

    @Test
    public void testFailSaveOnDuplicateUsername() {
        User user = new User("John", "Doe", "johndoe", "password");
        assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.saveAndFlush(user);
        });
    }

    @Test
    public void testFailSaveOnNullUsername() {
        User user = new User("John", "Doe", null, "password");
        assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.saveAndFlush(user);
        });
    }

    @Test
    public void testFailSaveOnNullPassword() {
        User user = new User("John", "Doe", "username", null);
        assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.saveAndFlush(user);
        });
    }

    @Test
    public void testFailSaveOnNullFirstName() {
        User user = new User(null, "Doe", "username", "password");
        assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.saveAndFlush(user);
        });
    }

    @Test
    public void testFailSaveOnNullLastName() {
        User user = new User("John", null, "username", "password");
        assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.saveAndFlush(user);
        });
    }

    @Test
    public void deleteUser() {
        userRepository.delete(user1);
        User user = userRepository.findOneByUserId(user1.getUserId());
        assertNull(user);
    }

    @Test
    public void updateUserOnDepositCash() {
        User user = userRepository.findByUsername("johndoe");
        user.depositCash(1000.0);
        userRepository.save(user);

        user = userRepository.findByUsername("johndoe");
        assertEquals(101000.0, user.getCash(), 0.0);
    }

    @Test
    public void updateUserOnWithdrawCash() {
        User user = userRepository.findByUsername("johndoe");
        user.withdrawCash(1000.0);
        userRepository.save(user);

        user = userRepository.findByUsername("johndoe");
        assertEquals(99000.0, user.getCash(), 0.0);
    }

    @Test
    public void testUpdateEmail() {
        User user = userRepository.findByUsername("johndoe");
        user.setEmail("email@email.com");
        userRepository.save(user);

        user = userRepository.findByUsername("johndoe");
        assertEquals("email@email.com", user.getEmail());
    }
}
