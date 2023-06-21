package OWurst.Investment_Simulator.EntityTests;

//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import OWurst.Investment_Simulator.General_Objects.Entity.User;

//  this file is my very first attempt at TDD, it will not be very elegant,
//  but I want it to be rigid and adhere to the TDD principles

@SpringBootTest
public class UserTests {
    @Test
    void testConstructorA() {
        User user = new User("owen", "uname", "pw");
        assertEquals(user.getPassword(), "pw");
        assertEquals(user.getUsername(), "uname");
        assertEquals(user.getName(), "owen");
    }

    @Test
    void testConstructorB() {
        User user = new User(0, "owen", "uname", "pw", null);
        assertEquals(user.getPassword(), "pw");
        assertEquals(user.getUsername(), "uname");
        assertEquals(user.getName(), "owen");
    }

    @Test
    void testConstructorsReturnStartingCash() {
        User user1 = new User("", "", "");

        assertEquals(user1.getTotalCash(), User.STARTING_CASH);
        assertEquals(user1.getTotalWorth(), User.STARTING_CASH);

        User user2 = new User(0, "", "", "", "");

        assertEquals(user2.getTotalCash(), User.STARTING_CASH);
        assertEquals(user2.getTotalWorth(), User.STARTING_CASH);
    }

    @Test
    void testSetAndGetEmail() {
        // constructor without email
        User user1 = new User("", "", "");
        assertEquals(user1.getEmail(), null);
        user1.setEmail("email");
        assertEquals(user1.getEmail(), "email");

        // constructor with email
        User user2 = new User(0, "", "", "", "howdy@hello");
        assertEquals(user2.getEmail(), "howdy@hello");
    }

    @Test
    void testSetCashAndSetWorth() {
        User user = new User(null, null, null);
        user.setTotalCash(100);
        assertEquals(user.getTotalCash(), 100);
        user.setTotalWorth(1000);
        assertEquals(user.getTotalWorth(), 1000);
    }
}
