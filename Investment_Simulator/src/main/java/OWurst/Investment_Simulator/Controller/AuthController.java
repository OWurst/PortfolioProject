package OWurst.Investment_Simulator.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import OWurst.Investment_Simulator.Constants.AddressConstants;
import OWurst.Investment_Simulator.DTO.LoginDTO;
import OWurst.Investment_Simulator.DTO.ReturnDTO;
import OWurst.Investment_Simulator.DTO.UserDTO;
import OWurst.Investment_Simulator.Service.AuthService;
import OWurst.Investment_Simulator.Utils.ReturnConstants;
import OWurst.Investment_Simulator.Utils.InputExceptions.IllegalRegistrationException;
import OWurst.Investment_Simulator.Utils.InputExceptions.InvalidLoginException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = AddressConstants.FRONTEND_URL, allowCredentials = "true")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/save")
    public ResponseEntity<ReturnDTO> saveUser(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        String username = userDTO.getUsername();
        int userId;

        try {
            userId = authService.addUser(userDTO);
        } catch (IllegalRegistrationException e) {
            return ResponseEntity.badRequest().body(ReturnConstants.handled400Error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ReturnConstants.unknownError(e.getMessage()));
        }

        setSessionAttributes(request, username, userId);

        return ResponseEntity.ok().body(ReturnConstants.simpleSuccess("Account created successfully", userId));
    }

    @PostMapping("/login")
    public ResponseEntity<ReturnDTO> loginUser(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        int userId;
        String username;

        try {
            userId = authService.loginUser(loginDTO);
            username = loginDTO.getUsername();
        } catch (InvalidLoginException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ReturnConstants.handled400Error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ReturnConstants.unknownError(e.getMessage()));
        }

        setSessionAttributes(request, username, userId);
        return ResponseEntity.ok().body(ReturnConstants.simpleSuccess("Login successful", userId));
    }

    @PostMapping("/logout")
    public ResponseEntity<ReturnDTO> logoutUser(HttpServletRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest().body(ReturnConstants.badSession());
        }

        try {
            request.getSession().getAttribute("USER_ID");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ReturnConstants.unverifiedSession());
        }

        try {
            request.getSession().invalidate();
            return ResponseEntity.ok().body(ReturnConstants.simpleSuccess("Logout successful", -1));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ReturnConstants.unknownError("Logout unsuccessful: " + e.getMessage()));
        }
    }

    private void setSessionAttributes(HttpServletRequest request, String username, int userId) {
        request.getSession().setAttribute("USERNAME", username);
        request.getSession().setAttribute("USER_ID", userId);
    }
}