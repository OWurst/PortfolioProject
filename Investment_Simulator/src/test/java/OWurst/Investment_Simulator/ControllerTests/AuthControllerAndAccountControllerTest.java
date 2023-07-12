package OWurst.Investment_Simulator.ControllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import OWurst.Investment_Simulator.Controller.AccountController;
import OWurst.Investment_Simulator.Controller.AuthController;
import OWurst.Investment_Simulator.DTO.LoginDTO;
import OWurst.Investment_Simulator.DTO.UserDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AuthControllerAndAccountControllerTest {
    @Autowired
    AuthController authController;

    @Autowired
    AccountController accountController;

    static MockHttpServletRequest request = new MockHttpServletRequest();

    @Test
    @Order(1)
    public void contextLoads() throws Exception {
        assertThat(authController).isNotNull();
    }

    @Test
    @Order(2)
    public void testSaveUserReturnsOKOnNormalInput() {
        UserDTO testDTO = new UserDTO("user", "pass", "firstname", "lastname", "email@");
        ResponseEntity<String> response = authController.saveUser(testDTO, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(3)
    public void testSaveUserFailsOnSameUsername() {
        UserDTO testDTO = new UserDTO("user", "pass", "firstname", "lastname", "email@");
        ResponseEntity<String> response = authController.saveUser(testDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Order(4)
    @Test
    void testSaveUserFailsOnNoPasswordOrNoUsername() {
        UserDTO badUserDTO = new UserDTO("userski", "", "Bobby", "Fishcher", "x@x");
        ResponseEntity<String> response = authController.saveUser(badUserDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        badUserDTO = new UserDTO("", "pass", "Bobby", "Fischer", "x@x");
        response = authController.saveUser(badUserDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(5)
    void testSaveUserFailsOnNoEmailOrNoName() {
        UserDTO userDTO = new UserDTO("userooni", "billiam", "ridderson", "", "x@x");
        ResponseEntity<String> response = authController.saveUser(userDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        userDTO = new UserDTO("userooni", "billiam", "oweno", "fletch", "");
        response = authController.saveUser(userDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(6)
    void testLoginFailsWithBadUsername() {
        LoginDTO badLoginDTO = new LoginDTO("", "pass");
        ResponseEntity<String> response = authController.loginUser(badLoginDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(7)
    void testLoginFailsWithBadPassword() {
        LoginDTO badLoginDTO = new LoginDTO("user", "fail");
        ResponseEntity<String> response = authController.loginUser(badLoginDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(8)
    void testLoginSucceedsWithLegalInfo() {
        LoginDTO loginDTO = new LoginDTO("user", "pass");
        ResponseEntity<String> response = authController.loginUser(loginDTO, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(10)
    void testLogoutSucceeds() {
        ResponseEntity<String> response = authController.logoutUser(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(11)
    void testDeleteUser() {
        LoginDTO loginDTO = new LoginDTO("user", "pass");
        authController.loginUser(loginDTO, request);

        ResponseEntity<String> response = accountController.deleteUser(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
