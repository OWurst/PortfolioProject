package OWurst.Investment_Simulator.ControllerUnitTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import OWurst.Investment_Simulator.Controller.AuthController;
import OWurst.Investment_Simulator.DTO.LoginDTO;
import OWurst.Investment_Simulator.DTO.ReturnDTO;
import OWurst.Investment_Simulator.DTO.UserDTO;
import OWurst.Investment_Simulator.Service.AuthService;
import OWurst.Investment_Simulator.Utils.ReturnConstants;
import OWurst.Investment_Simulator.Utils.InputExceptions.IllegalRegistrationException;
import jakarta.servlet.http.HttpSession;

@ExtendWith(MockitoExtension.class)

public class AuthControllerUnitTest {
    AuthController authController;
    AuthService authService;

    MockHttpServletRequest request;
    HttpSession session;

    int testUid;
    UserDTO userDTO;
    LoginDTO loginDTO;
    ReturnDTO returnDTO;
    ReturnDTO expectedReturnDTO;
    ResponseEntity<ReturnDTO> response;

    @Before
    public void setUp() {
        testUid = 1;

        userDTO = new UserDTO();
        userDTO.setUsername("test_username");

        loginDTO = new LoginDTO();
        loginDTO.setUsername("test_username");

        request = new MockHttpServletRequest();
        authService = mock(AuthService.class);
    }

    @Test
    public void testSaveUserOutputOnSuccess() throws Exception {
        when(authService.addUser(userDTO)).thenReturn(testUid);
        authController = new AuthController(authService);

        response = authController.saveUser(userDTO, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        returnDTO = response.getBody();
        expectedReturnDTO = ReturnConstants.simpleSuccess("Account created successfully", testUid);

        try {
            assertEquals(expectedReturnDTO.getMsg(), returnDTO.getMsg());
            assertEquals(expectedReturnDTO.getRespCode(), returnDTO.getRespCode());
            assertEquals(expectedReturnDTO.getUid(), returnDTO.getUid());

            session = request.getSession();
            assertEquals(testUid, session.getAttribute("USER_ID"));
            assertEquals(userDTO.getUsername(), session.getAttribute("USERNAME"));
        } catch (Exception e) {
            fail();
        }
    }

    @SuppressWarnings("null")
    @Test
    public void testSaveUserOutputOnIllegalInputException() throws Exception {
        when(authService.addUser(userDTO)).thenThrow(new IllegalRegistrationException("Test exception"));
        authController = new AuthController(authService);

        response = authController.saveUser(userDTO, request);

        try {
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertEquals("Test exception", response.getBody().getErrorMsg());
        } catch (Exception e) {
            fail();
        }
    }

    @SuppressWarnings("null")
    @Test
    public void testSaveUserOutputOnUnknownException() throws Exception {
        when(authService.addUser(userDTO)).thenThrow(new Exception("Test exception"));
        authController = new AuthController(authService);

        response = authController.saveUser(userDTO, request);

        try {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
            assertEquals("Test exception", response.getBody().getErrorMsg());
        } catch (Exception e) {
            fail();
        }
    }

    @SuppressWarnings("null")
    @Test
    public void testLogoutSuccessful() {
        authController = new AuthController(authService);

        try {
            request.getSession().setAttribute("USER_ID", testUid);
            request.getSession().setAttribute("USERNAME", "test_username");
        } catch (Exception e) {
            fail();
        }

        response = authController.logoutUser(request);
        returnDTO = response.getBody();

        expectedReturnDTO = ReturnConstants.simpleSuccess("Logout successful", -1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedReturnDTO.getMsg(), returnDTO.getMsg());
        assertEquals(expectedReturnDTO.getRespCode(), returnDTO.getRespCode());
        assertEquals(expectedReturnDTO.getUid(), returnDTO.getUid());

        session = request.getSession(false);
        if (session != null) {
            Object userId = request.getSession().getAttribute("USER_ID");
            Object username = request.getSession().getAttribute("USERNAME");

            assertNull(userId);
            assertNull(username);
        } else {
        }
    }

    @Test
    public void testLogoutIllegalStateException() {

    }

    @Test
    public void testLogoutUnverifiedSession() {

    }

    @Test
    public void testLogoutNullSession() {

    }
}
