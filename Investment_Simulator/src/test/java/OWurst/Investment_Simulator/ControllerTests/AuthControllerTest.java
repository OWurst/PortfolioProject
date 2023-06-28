package OWurst.Investment_Simulator.ControllerTests;

import org.junit.jupiter.api.BeforeEach;
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
public class AuthControllerTest {
    @Autowired
    AuthController authController;

    static LoginDTO loginDTO;
    static UserDTO userDTO;
    static MockHttpServletRequest request;

    @BeforeEach
    public void setupBeforeClass() {
        if (loginDTO == null)
            loginDTO = new LoginDTO("user", "pass");
        if (userDTO == null)
            userDTO = new UserDTO(0, "user", "pass", "name", "email");
        if (request == null)
            request = new MockHttpServletRequest();
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(authController).isNotNull();
    }

    @Test
    public void testSaveUserReturnsOKOnNormalInput() {
        ResponseEntity<String> response = authController.saveUser(userDTO, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSaveUserFailsOnSameUsername() {
        ResponseEntity<String> response = authController.saveUser(userDTO, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testSaveUserFailsOnNoPasswordOrNoUsername() {
    }

    @Test
    void testSaveUserFailsOnNoEmailOrNoUsername() {
    }

    @Test
    void testLoginSucceedsWithLegalInfo() {
    }

    @Test
    void testLoginFailsWithBadUsername() {
    }

    @Test
    void testLoginFailsWithBadPassword() {
    }

    @Test
    void testLogoutSucceeds() {
    }

    @Test
    void testGetUsernameSucceedsWhenValidUser() {
    }

    @Test
    void testGetUsernameFailsWhenInvalidUser() {
    }

    @AfterTestClass
    void teardownAfterClass() {
        // this will likely have to be changed
    }
}
