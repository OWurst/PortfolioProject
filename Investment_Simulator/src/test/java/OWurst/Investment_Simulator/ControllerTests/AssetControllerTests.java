package OWurst.Investment_Simulator.ControllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import OWurst.Investment_Simulator.Controller.AssetController;
import OWurst.Investment_Simulator.Controller.AuthController;
import OWurst.Investment_Simulator.DTO.LoginDTO;
import OWurst.Investment_Simulator.DTO.UserDTO;
import OWurst.Investment_Simulator.Entity.Assets;
import OWurst.Investment_Simulator.Repository.UserRepository;

@SpringBootTest
public class AssetControllerTests {
    @Autowired
    AssetController assetController;

    @Autowired
    AuthController authController;

    @Autowired
    UserRepository userRepository;

    static Assets assets;

    static MockHttpServletRequest request = new MockHttpServletRequest();

    @Test
    @Order(1)
    public void testSaveUserReturnsOKOnNormalInput() {
        UserDTO testDTO = new UserDTO("user", "Password1$", "firstname", "lastname", "email@gmail.com");
        ResponseEntity<String> response = authController.saveUser(testDTO, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(2)
    void getNewUserCash() {
        loginUser();
        ResponseEntity<Double> response = assetController.getCash(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(100000.00, response.getBody());
    }

    void loginUser() {
        LoginDTO loginDTO = new LoginDTO("user", "Password1$");
        authController.loginUser(loginDTO, request);
    }
}
