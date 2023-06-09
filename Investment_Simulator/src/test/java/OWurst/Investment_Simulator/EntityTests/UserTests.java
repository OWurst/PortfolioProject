package OWurst.Investment_Simulator.EntityTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import OWurst.Investment_Simulator.Entity.User;

@SpringBootTest
public class UserTests {
    @Test
    void testConstructorA() {
        User user = new User("owen", "wurst", "uname", "pw");
        assertEquals(user.getPassword(), "pw");
        assertEquals(user.getUsername(), "uname");
        assertEquals(user.getFirstName(), "owen");
        assertEquals(user.getLastName(), "wurst");
    }

    @Test
    void testConstructorB() {
        User user = new User("owen", "wurst", "uname", "pw", null);
        assertEquals(user.getPassword(), "pw");
        assertEquals(user.getUsername(), "uname");
        assertEquals(user.getFirstName(), "owen");
        assertEquals(user.getLastName(), "wurst");
    }

    @Test
    void testConstructorsReturnStartingCash() {
        User user1 = new User("", "", "", "");

        assertEquals(user1.getCash(), User.STARTING_CASH);

        User user2 = new User("", "", "", "");

        assertEquals(user2.getCash(), User.STARTING_CASH);
    }

    @Test
    void testSetAndGetEmail() {
        // constructor without email
        User user1 = new User("", "", "", "");
        assertEquals(null, user1.getEmail());
        user1.setEmail("email");
        assertEquals(user1.getEmail(), "email");

        // constructor with email
        User user2 = new User("", "", "", "", "howdy@hello");
        assertEquals(user2.getEmail(), "howdy@hello");
    }
}
