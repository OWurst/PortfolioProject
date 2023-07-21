package OWurst.Investment_Simulator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import OWurst.Investment_Simulator.DTO.ChangePWDTO;
import OWurst.Investment_Simulator.DTO.LoginDTO;
import OWurst.Investment_Simulator.DTO.UserDTO;
import OWurst.Investment_Simulator.Entity.User;
import OWurst.Investment_Simulator.Repository.UserRepository;

@Service
public class UserServiceImpl implements AuthService, AccountService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<String> addUser(UserDTO userDTO, HttpServletRequest request) {
        String body;
        HttpStatus status;

        if (userRepository.findByUsername(userDTO.getUsername()) == null) {
            if (userDTOFieldsAreAllLegal(userDTO)) {
                User user = new User(userDTO.getFirstname(), userDTO.getLastname(),
                        userDTO.getUsername(),
                        this.passwordEncoder.encode(userDTO.getPassword()), userDTO.getEmail());

                userRepository.save(user);

                request.getSession().setAttribute("USERNAME", user.getUsername());
                request.getSession().setAttribute("USER_ID", user.getId());

                body = "Registration successful";
                status = HttpStatus.OK;
                return new ResponseEntity<>(body, status);
            } else {
                body = "Registration unsuccessful: Please double check all fields";
                status = HttpStatus.BAD_REQUEST;
            }
        } else {
            body = "Registration unsuccessful: username already taken";
            status = HttpStatus.BAD_REQUEST;
        }
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
    public ResponseEntity<String> deleteUser(HttpServletRequest request) {
        if (request == null) {
            return new ResponseEntity<>("Error: Session could not be found.", HttpStatus.BAD_REQUEST);
        }
        try {
            User user = userRepository.findOneById((int) request.getSession().getAttribute("USER_ID"));
            userRepository.delete(user);
        }
        // TODO figure out what the two exceptions are (one for username not found and
        // one for failed delete, and handle errors separately)
        catch (Exception e) {
            return new ResponseEntity<>("Error: Delete failed", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Success: Account deleted", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> loginUser(LoginDTO loginDTO, HttpServletRequest request) {
        String body;
        HttpStatus status;

        User user = userRepository.findByUsername(loginDTO.getUsername());

        if (user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            request.getSession().setAttribute("USERNAME", user.getUsername());
            request.getSession().setAttribute("USER_ID", user.getId());

            body = "Login successful";
            status = HttpStatus.OK;
        } else {
            body = "Login unsuccessful: Invalid username or password";
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(body, status);
    }

    @Override
    public ResponseEntity<String> logoutUser(HttpServletRequest request) {
        String body;
        HttpStatus status;

        try {
            request.getSession().invalidate();
            body = "Logout successful";
            status = HttpStatus.OK;
        } catch (IllegalStateException e) {
            body = "Logout unsuccessful: " + e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(body, status);
    }

    @Override
    public ResponseEntity<String> changePassword(ChangePWDTO changePWDTO, HttpServletRequest request) {
        User user = userRepository.findOneById((int) request.getSession().getAttribute("USER_ID"));
        if (!validPassword(changePWDTO.getNewpassword())) {
            return ResponseEntity.badRequest().body("Failed: invalid password");
        }
        if (passwordEncoder.matches(changePWDTO.getOldpassword(), user.getPassword())) {
            user.setPassword(this.passwordEncoder.encode(changePWDTO.getNewpassword()));
            userRepository.save(user);
            return ResponseEntity.ok().body("Success saving new password");
        }
        return ResponseEntity.badRequest().body("Failed: incorrect password");
    }

    @Override
    public ResponseEntity<String> getUserObject(HttpServletRequest request) {
        User user = userRepository.findOneById((int) request.getSession().getAttribute("USER_ID"));

        String msg = "{\"username\": \"" + user.getUsername() + "\", \"firstname\": \"" + user.getFirstName() + "\","
                + "\"lastname\": \"" + user.getLastName() + "\"," +
                "\"email\": \"" + user.getEmail() + "\"}";

        return ResponseEntity.ok().body(msg);

    }

    @Override
    public ResponseEntity<String> changeEmail(String email, HttpServletRequest request) {
        User user = userRepository.findOneById((int) request.getSession().getAttribute("USER_ID"));
        if (!validEmail(email)) {
            return ResponseEntity.badRequest().body("Failed: invalid email");
        }
        user.setEmail(email);
        userRepository.save(user);
        return ResponseEntity.ok().body("Success saving new password");
    }

    private boolean userDTOFieldsAreAllLegal(UserDTO userDTO) {
        if (validUsername(userDTO.getUsername()) &&
                validPassword(userDTO.getPassword()) &&
                validName(userDTO.getFirstname()) &&
                validName(userDTO.getFirstname()) &&
                validEmail(userDTO.getEmail())) {
            return true;
        }
        return false;
    }

    private boolean validEmail(String email) {
        return org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(email);
    }

    private boolean validUsername(String username) {
        return (username.length() >= 4) && (username.length() < 20);
    }

    private boolean validName(String name) {
        return (name.length() >= 2) && (name.length() >= 15);
    }

    private boolean validPassword(String pw) {
        if (pw.length() < 8) {
            return false;
        }
        if (!containsLowerCase(pw)) {
            return false;
        }
        if (!containsUpperCase(pw)) {
            return false;
        }
        if (!containsSpecialCharacter(pw)) {
            return false;
        }
        if (!containsNumber(pw)) {
            return false;
        }
        return true;
    }

    private boolean containsSpecialCharacter(String pw) {
        String[] specChars = { "!", "@", "#", "$", "%", "^", "&", "*", "?" };
        for (int i = 0; i < specChars.length; i++) {
            if (pw.contains(specChars[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean containsUpperCase(String pw) {
        if (pw.length() > 7 && pw.length() < 20) {
            for (int i = 0; i < pw.length(); i++) {
                if (pw.charAt(i) >= 65 && pw.charAt(i) <= 90) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containsLowerCase(String pw) {
        for (int i = 0; i < pw.length(); i++) {
            if (pw.charAt(i) >= 97 && pw.charAt(i) <= 122) {
                return true;
            }
        }
        return false;
    }

    private boolean containsNumber(String pw) {
        for (int i = 0; i < pw.length(); i++) {
            if (pw.charAt(i) >= 48 && pw.charAt(i) <= 57) {
                return true;
            }
        }
        return false;
    }
}
