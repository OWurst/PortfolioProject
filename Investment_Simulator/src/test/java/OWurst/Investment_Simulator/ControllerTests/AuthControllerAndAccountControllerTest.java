package OWurst.Investment_Simulator.ControllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import OWurst.Investment_Simulator.Controller.AccountController;
import OWurst.Investment_Simulator.Controller.AuthController;
import OWurst.Investment_Simulator.DTO.ChangePWDTO;
import OWurst.Investment_Simulator.DTO.LoginDTO;
import OWurst.Investment_Simulator.DTO.UserDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuthControllerAndAccountControllerTest {
    @Autowired
    AuthController authController;

    @Autowired
    AccountController accountController;

    static MockHttpServletRequest request = new MockHttpServletRequest();

    @Test
    @Order(1)
    void contextLoads() throws Exception {
        assertThat(authController).isNotNull();
    }

    @Test
    @Order(2)
    void saveUserReturnsOKOnNormalInput() {
        UserDTO testDTO = new UserDTO("user", "Password1$", "firstname", "lastname", "email@gmail.com");
        ResponseEntity<String> response = authController.saveUser(testDTO, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(3)
    void saveUserFailsOnSameUsername() {
        UserDTO testDTO = new UserDTO("user", "Password1$", "firstname", "lastname", "email@gmail.com");
        ResponseEntity<String> response = authController.saveUser(testDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Order(4)
    @Test
    void saveUserFailsOnNoPasswordOrNoUsername() {
        UserDTO badUserDTO = new UserDTO("userski", "", "Bobby", "Fishcher", "x@x");
        ResponseEntity<String> response = authController.saveUser(badUserDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        badUserDTO = new UserDTO("", "pass", "Bobby", "Fischer", "x@x");
        response = authController.saveUser(badUserDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(5)
    void saveUserFailsOnNoEmailOrNoName() {
        UserDTO userDTO = new UserDTO("userooni", "billiam$Y", "ridderson", "", "x@x");
        ResponseEntity<String> response = authController.saveUser(userDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        userDTO = new UserDTO("userooni", "billiam", "oweno", "fletch", "");
        response = authController.saveUser(userDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(6)
    void loginFailsWithBadUsername() {
        LoginDTO badLoginDTO = new LoginDTO("", "Password1$");
        ResponseEntity<String> response = authController.loginUser(badLoginDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    // @Test
    // @Order(7)
    // void loginFailsWithBadPassword() {
    // LoginDTO badLoginDTO = new LoginDTO("user", "fail");
    // ResponseEntity<String> response = authController.loginUser(badLoginDTO,
    // request);
    // assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    // }

    // @Test
    // @Order(8)
    // void loginSucceedsWithLegalInfo() {
    // LoginDTO loginDTO = new LoginDTO("user", "Password1$");
    // ResponseEntity<String> response = authController.loginUser(loginDTO,
    // request);
    // assertEquals(HttpStatus.OK, response.getStatusCode());
    // }

    // @Test
    // @Order(9)
    // void changePWSucceedsWithLegalInfo() {
    // loginUser();

    // ChangePWDTO change = new ChangePWDTO("Password1$", "Password2$");
    // ResponseEntity<String> response = accountController.updatePassword(change,
    // request);
    // assertEquals(HttpStatus.OK, response.getStatusCode());
    // LoginDTO loginDTO = new LoginDTO("user", "Password2$");
    // response = authController.loginUser(loginDTO, request);
    // assertEquals(HttpStatus.OK, response.getStatusCode());

    // change = new ChangePWDTO("Password2$", "Password1$");
    // response = accountController.updatePassword(change, request);
    // assertEquals(HttpStatus.OK, response.getStatusCode());
    // }

    void testChangePWFailsWithIllegalInfo() {
        loginUser();

        ChangePWDTO change;
        ResponseEntity<String> response;

        change = new ChangePWDTO("Password2$", "Password2$");
        response = accountController.updatePassword(change, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        change = new ChangePWDTO("Password1$", "Password2");
        response = accountController.updatePassword(change, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        change = new ChangePWDTO("Password$", "Password2$");
        response = accountController.updatePassword(change, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        change = new ChangePWDTO("Password1$", "password2$");
        response = accountController.updatePassword(change, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        change = new ChangePWDTO("Password1$", "PASSWORD2$");
        response = accountController.updatePassword(change, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        change = new ChangePWDTO("Password1$", "Pd2$");
        response = accountController.updatePassword(change, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(11)
    void changeEmailWorksWithLegalEmail() {
        loginUser();
        ResponseEntity<String> response = accountController.updateEmail("bobby@gmail.com", request);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ObjectMapper mapper = new ObjectMapper();
        UserDTO user = null;
        try {
            user = mapper.readValue(accountController.getUserObject(request).getBody(), UserDTO.class);
        } catch (Exception e) {
        }
        assertEquals("bobby@gmail.com", user.getEmail());
    }

    @Test
    @Order(14)
    void logoutSucceeds() {
        loginUser();

        ResponseEntity<String> response = authController.logoutUser(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(15)
    void deleteUser() {
        loginUser();

        ResponseEntity<String> response = accountController.deleteUser(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    void loginUser() {
        LoginDTO loginDTO = new LoginDTO("user", "Password1$");
        authController.loginUser(loginDTO, request);
    }
}
