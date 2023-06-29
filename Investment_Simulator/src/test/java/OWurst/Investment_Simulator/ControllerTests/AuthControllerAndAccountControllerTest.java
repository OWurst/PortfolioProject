package OWurst.Investment_Simulator.ControllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestClass;

import OWurst.Investment_Simulator.Controller.AuthController;
import OWurst.Investment_Simulator.DTO.LoginDTO;
import OWurst.Investment_Simulator.DTO.UserDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AuthControllerAndAccountControllerTest {
    @Autowired
    AuthController authController;

    static MockHttpServletRequest request = new MockHttpServletRequest();

    @Test
    public void contextLoads() throws Exception {
        assertThat(authController).isNotNull();
    }

    @Test
    public void testSaveUserReturnsOKOnNormalInput() {
        UserDTO testDTO = new UserDTO(0, "user", "pass", "name", "email@");
        ResponseEntity<String> response = authController.saveUser(testDTO, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSaveUserFailsOnSameUsername() {
        UserDTO testDTO = new UserDTO(0, "user", "pass", "name", "email@");
        ResponseEntity<String> response = authController.saveUser(testDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testSaveUserFailsOnNoPasswordOrNoUsername() {
        UserDTO badUserDTO = new UserDTO(1, "userski", "", "Bobby", "x@x");
        ResponseEntity<String> response = authController.saveUser(badUserDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        badUserDTO = new UserDTO(1, "", "pass", "Bobby", "x@x");
        response = authController.saveUser(badUserDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testSaveUserFailsOnNoEmailOrNoName() {
        UserDTO userDTO = new UserDTO(1, "userooni", "billiam", "", "x@x");
        ResponseEntity<String> response = authController.saveUser(userDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        userDTO = new UserDTO(1, "userooni", "billiam", "oweno", "");
        response = authController.saveUser(userDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testLoginSucceedsWithLegalInfo() {
        LoginDTO loginDTO = new LoginDTO("user", "pass");
        ResponseEntity<String> response = authController.loginUser(loginDTO, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testLoginFailsWithBadUsername() {
        LoginDTO badLoginDTO = new LoginDTO("", "pass");
        ResponseEntity<String> response = authController.loginUser(badLoginDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testLoginFailsWithBadPassword() {
        LoginDTO badLoginDTO = new LoginDTO("user", "fail");
        ResponseEntity<String> response = authController.loginUser(badLoginDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testLogoutSucceeds() {
        ResponseEntity<String> response = authController.logoutUser(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    void testLogoutFailsOnNullRequest() {
        ResponseEntity<String> response = authController.logoutUser(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testGetUsernameSucceedsWhenValidUser() {
        ResponseEntity<String> response = authController.getUsername(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetUsernameFailsWhenNullUser() {
        ResponseEntity<String> response = authController.getUsername(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @AfterTestClass
    void teardownAfterClass() {
        // this will likely have to be changed
        // will become delete method for database user entry
    }
}
