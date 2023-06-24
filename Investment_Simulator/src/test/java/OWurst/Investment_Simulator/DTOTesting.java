package OWurst.Investment_Simulator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import OWurst.Investment_Simulator.DTO.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

//  while I do not expect this testing suite to ever be relevant, it is a simple safeguard to remind me to update DTOs should
//  I ever change one of the classes they are used for and add/remove variables 06/23/2023

@SpringBootTest
public class DTOTesting {
    @Test
    void runTestsForAllDTOs() {
        LoginDTOTests.runTests("user", "pass");
        UserDTOTests.runTests("user", "pass", "name", "email", 0, 100.0);
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
        static UserDTO inConstructor;
        static UserDTO outConstructor;

        static int id;

        static String name;
        static String username;
        static String password;
        static String email;
        static double totalCash;
        static double totalWorth;

        private static void runTests(String inUser, String inPass, String inName, String inEmail, int inId,
                double inCash) {
            name = inName;
            password = inPass;
            username = inUser;
            email = inEmail;
            totalCash = inCash;
            totalWorth = inCash + 100.0;
            id = inId;

            inConstructor = new UserDTO(id, username, password, name, email);
            outConstructor = new UserDTO(id, totalCash, totalWorth);

            testInConstructor();
            testOutConstructor();
            testGetAndSetCash();
        }

        private static void testInConstructor() {
            assertEquals(name, inConstructor.getName());
            assertEquals(username, inConstructor.getUsername());
            assertEquals(id, inConstructor.getId());
            assertEquals(password, inConstructor.getPassword());
            assertEquals(email, inConstructor.getEmail());
        }

        private static void testOutConstructor() {
            assertEquals(id, outConstructor.getId());
            assertEquals(totalCash, outConstructor.getTotalCash());
            assertEquals(totalWorth, outConstructor.getTotalWorth());
            assertEquals(null, outConstructor.getUsername());
        }

        private static void testGetAndSetCash() {
            assertEquals(0.0, inConstructor.getTotalWorth());
            assertEquals(0.0, inConstructor.getTotalCash());

            inConstructor.setTotalCash(totalCash);
            inConstructor.setTotalWorth(totalWorth);

            assertEquals(totalWorth, inConstructor.getTotalWorth());
            assertEquals(totalCash, inConstructor.getTotalCash());
        }
    }
}