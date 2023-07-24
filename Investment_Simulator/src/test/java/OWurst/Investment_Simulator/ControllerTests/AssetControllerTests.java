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

    Assets assets;

    static MockHttpServletRequest request = new MockHttpServletRequest();

    @BeforeTestClass
    void createTestUser() {
        testSaveUserReturnsOKOnNormalInput();
    }

    @Test
    void getCashAfterContstruction() {
        assertEquals(100000.0, assets.getCash());// fail
        assets = userRepository.findOneById((int) request.getSession().getAttribute("USER_ID")).getAssets();
    }

    public void testSaveUserReturnsOKOnNormalInput() {
        UserDTO testDTO = new UserDTO("user", "Password1$", "firstname", "lastname", "email@gmail.com");
        ResponseEntity<String> response = authController.saveUser(testDTO, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
