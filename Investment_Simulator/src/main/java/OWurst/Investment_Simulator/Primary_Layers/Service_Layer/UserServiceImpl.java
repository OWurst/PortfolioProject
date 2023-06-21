package OWurst.Investment_Simulator.Primary_Layers.Service_Layer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

import OWurst.Investment_Simulator.General_Objects.DTO.UserDTO;
import OWurst.Investment_Simulator.General_Objects.DTO.LoginDTO;
import OWurst.Investment_Simulator.General_Objects.Entity.User;
import OWurst.Investment_Simulator.Primary_Layers.DB_Layer.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<String> addUser(UserDTO userDTO, HttpServletRequest request) {
        // Response body and status
        String body;
        HttpStatus status;

        // Search to see if username is taken
        if (userRepository.findByUsername(userDTO.getUsername()) == null) {
            // Create new user and add to repository
            User user = new User(userDTO.getId(), userDTO.getName(), userDTO.getUsername(),
                    this.passwordEncoder.encode(userDTO.getPassword()), userDTO.getEmail());
            userRepository.save(user);

            // Add username and id to sessions
            request.getSession().setAttribute("USERNAME", user.getUsername());
            request.getSession().setAttribute("USER_ID", user.getId());

            // Update response to show success
            body = "Registration successful";
            status = HttpStatus.OK;
        } else {
            // Update response to show failure
            body = "Registration unsuccessful: username already taken";
            status = HttpStatus.BAD_REQUEST;
        }

        // Return response
        return new ResponseEntity<>(body, status);
    }

    @Override
    public ResponseEntity<String> getUsername(HttpServletRequest request) {
        User user = userRepository.findOneById((int) request.getSession().getAttribute("USER_ID"));
        return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> loginUser(LoginDTO loginDTO, HttpServletRequest request) {
        // Response body and status
        String body;
        HttpStatus status;

        // Search for user in db
        User user = userRepository.findByUsername(loginDTO.getUsername());

        // If user found, authenticate password
        if (user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            // Add username and id to sessions
            request.getSession().setAttribute("USERNAME", user.getUsername());
            request.getSession().setAttribute("USER_ID", user.getId());

            // Update response to show success
            body = "Login successful";
            status = HttpStatus.OK;
        } else {
            // Update response to show failure
            body = "Login unsuccessful: Invalid username or password";
            status = HttpStatus.BAD_REQUEST;
        }

        // Return response
        return new ResponseEntity<>(body, status);
    }

    @Override
    public ResponseEntity<String> logoutUser(HttpServletRequest request) {
        // Response body and status
        String body;
        HttpStatus status;

        // Invalidate user session
        try {
            request.getSession().invalidate();
            body = "Logout successful";
            status = HttpStatus.OK;
        } catch (IllegalStateException e) {
            body = "Logout unsuccessful: " + e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }

        // Return response
        return new ResponseEntity<>(body, status);
    }
}
