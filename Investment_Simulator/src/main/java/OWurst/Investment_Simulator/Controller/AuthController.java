package OWurst.Investment_Simulator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import OWurst.Investment_Simulator.DTO.LoginDTO;
import OWurst.Investment_Simulator.DTO.UserDTO;
import OWurst.Investment_Simulator.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        return userService.addUser(userDTO, request);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        return userService.loginUser(loginDTO, request);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logOutUser(HttpServletRequest request) {
        return userService.logoutUser(request);
    }

    @GetMapping("/get-username")
    public ResponseEntity<String> getUsername(HttpServletRequest request) {
        return userService.getUsername(request);
    }
}