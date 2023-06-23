package OWurst.Investment_Simulator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import OWurst.Investment_Simulator.General_Objects.DTO.*;

public class DTOTesting {

    @Test
    void runTestsForAllDTOs() {
        LoginDTOTests.runTests("user", "pass");
    }

    private class LoginDTOTests {
        static LoginDTO input;
        static String user;
        static String pass;

        private static void runTests(String username, String password) {
            input = new LoginDTO(username, password);
            user = username;
            pass = password;
            testEmptyConstructor();
            testFullConstructor();
        }

        private static void testEmptyConstructor() {
            LoginDTO empty = new LoginDTO();

            empty.setPassword(pass);
            assertEquals(empty.getPassword(), pass);
            empty.setUsername(user);
            assertEquals(empty.getUsername(), user);
        }

        private static void testFullConstructor() {
            assertEquals(input.getPassword(), pass);
            assertEquals(input.getUsername(), user);
        }
    }

    private class UserDTOTests {
        void runTests() {
        }
    }
}