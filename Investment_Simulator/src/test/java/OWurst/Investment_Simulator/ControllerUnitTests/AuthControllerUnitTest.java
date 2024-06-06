package OWurst.Investment_Simulator.ControllerUnitTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

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
import jakarta.servlet.http.HttpSession;

@ExtendWith(MockitoExtension.class)

public class AuthControllerUnitTest {
    int testUid;
    UserDTO userDTO;
    LoginDTO loginDTO;

    MockHttpServletRequest request;

    @Before
    public void setUp() {
        testUid = 1;

        userDTO = new UserDTO();
        userDTO.setUsername("test_username");

        loginDTO = new LoginDTO();
        loginDTO.setUsername("test_username");

        request = new MockHttpServletRequest();
    }

    @SuppressWarnings("null")
    @Test
    public void testSaveUserOutputOnSuccess() throws Exception {
        AuthService authService = mock(AuthService.class);
        when(authService.addUser(userDTO)).thenReturn(testUid);
        AuthController authController = new AuthController(authService);

        ResponseEntity<ReturnDTO> response = authController.saveUser(userDTO, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ReturnDTO returnDTO = response.getBody();
        ReturnDTO expectedReturnDTO = ReturnConstants.simpleSuccess("Account created successfully", testUid);

        try {
            assertEquals(expectedReturnDTO.getMsg(), returnDTO.getMsg());
            assertEquals(expectedReturnDTO.getRespCode(), returnDTO.getRespCode());
            assertEquals(expectedReturnDTO.getUid(), returnDTO.getUid());

            HttpSession session = request.getSession();
            assertEquals(testUid, session.getAttribute("USER_ID"));
            assertEquals(userDTO.getUsername(), session.getAttribute("USERNAME"));
        } catch (Exception e) {
            fail();
        }
    }
}
