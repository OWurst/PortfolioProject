package OWurst.Investment_Simulator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import OWurst.Investment_Simulator.DTO.LoginDTO;
import OWurst.Investment_Simulator.DTO.UserDTO;
import OWurst.Investment_Simulator.Entity.User;
import OWurst.Investment_Simulator.Repository.UserRepository;

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
            if (userDTOFieldsAreAllLegal(userDTO)) {
                User user = new User(userDTO.getId(), userDTO.getName(),
                        userDTO.getUsername(),
                        this.passwordEncoder.encode(userDTO.getPassword()), userDTO.getEmail());

                userRepository.save(user);

                // Add username and id to sessions
                request.getSession().setAttribute("USERNAME", user.getUsername());
                request.getSession().setAttribute("USER_ID", user.getId());

                // Update response to show success
                body = "Registration successful";
                status = HttpStatus.OK;
                return new ResponseEntity<>(body, status);
            } else {
                body = "Registration unsuccessful: Please double check all fields";
                status = HttpStatus.BAD_REQUEST;
            }
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
        if (request == null) {
            return new ResponseEntity<>("Error: Session could not be found.", HttpStatus.BAD_REQUEST);
        }

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

    private boolean userDTOFieldsAreAllLegal(UserDTO userDTO) {
        if (userDTO.getUsername() == null || userDTO.getUsername().equals("")) {
            System.out.println("\n\nHmmm\n\n");
            return false;
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().equals("")) {
            System.out.println("\n\na\n\n");
            return false;
        }
        if (userDTO.getName() == null || userDTO.getName().equals("")) {
            System.out.println("\n\nb\n\n");
            return false;
        }
        if (userDTO.getEmail() == null || userDTO.getEmail().equals("")) {
            System.out.println("\n\nc\n\n");
            return false;
        }

        return true;
    }
}
