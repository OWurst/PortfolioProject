package OWurst.Investment_Simulator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import OWurst.Investment_Simulator.DTO.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

//  while I do not expect this testing suite to ever be relevant, it is a simple safeguard to remind me to update DTOs should
//  I ever change one of the classes they are used for and add/remove variables 06/23/2023

@SpringBootTest
class DTOTesting {
    @Test
    void runTestsForAllDTOs() {
        LoginDTOTests.runTests("user", "pass");
        UserDTOTests.runTests("user", "pass", "firstname", "lastname", "email", 0);
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

        static int id;

        static String firstname;
        static String lastname;
        static String username;
        static String password;
        static String email;

        private static void runTests(String inUser, String inPass, String inFirstName, String inLastName,
                String inEmail, int inId) {
            firstname = inFirstName;
            lastname = inLastName;

            password = inPass;
            username = inUser;
            email = inEmail;
            id = inId;

            inConstructor = new UserDTO(username, password, firstname, lastname, email);

            testInConstructor();
        }

        private static void testInConstructor() {
            assertEquals(firstname, inConstructor.getFirstname());
            assertEquals(lastname, inConstructor.getLastname());
            assertEquals(username, inConstructor.getUsername());
            assertEquals(id, inConstructor.getId());
            assertEquals(password, inConstructor.getPassword());
            assertEquals(email, inConstructor.getEmail());
        }
    }
}