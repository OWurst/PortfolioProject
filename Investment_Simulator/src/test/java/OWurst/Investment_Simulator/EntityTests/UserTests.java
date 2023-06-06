package OWurst.Investment_Simulator.EntityTests;

import OWurst.Investment_Simulator.Entity.User;

//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//  this file is my very first attempt at TDD, it will not be very elegant,
//  but I want it to be rigid and adhere to the TDD principles

@SpringBootTest
public class UserTests {
    @Test
    void constructorsReturnStartingCash() {
        User user1 = new User("", "", "");

        assertEquals(user1.getTotalCash(), User.STARTING_CASH);
        assertEquals(user1.getTotalWorth(), User.STARTING_CASH);

        User user2 = new User("", "", "", "");

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
        User user2 = new User("", "", "", "howdy@hello");
        assertEquals(user2.getEmail(), "howdy@hello");
    }
}
