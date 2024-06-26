package OWurst.Investment_Simulator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

import OWurst.Investment_Simulator.DTO.ChangePWDTO;
import OWurst.Investment_Simulator.DTO.LoginDTO;
import OWurst.Investment_Simulator.DTO.ReturnBuilder;
import OWurst.Investment_Simulator.DTO.ReturnDTO;
import OWurst.Investment_Simulator.DTO.UserDTO;
import OWurst.Investment_Simulator.Entity.User;
import OWurst.Investment_Simulator.Repository.UserRepository;
import OWurst.Investment_Simulator.Utils.InputExceptions.IllegalRegistrationException;
import OWurst.Investment_Simulator.Utils.InputExceptions.InvalidLoginException;
import OWurst.Investment_Simulator.Utils.ReturnConstants;
import OWurst.Investment_Simulator.Utils.SessionData;

@Service
public class UserServiceImpl implements AuthService, AccountService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public int addUser(UserDTO userDTO) throws Exception {
        if (userRepository.findByUsername(userDTO.getUsername()) == null) {
            if (userDTOFieldsAreAllLegal(userDTO)) {
                User user = new User(userDTO.getFirstname(), userDTO.getLastname(),
                        userDTO.getUsername(),
                        this.passwordEncoder.encode(userDTO.getPassword()), userDTO.getEmail());

                userRepository.save(user);
                return user.getUserId();
            } else {
                throw new IllegalRegistrationException("Registration unsuccessful: invalid fields");
            }
        } else {
            throw new IllegalRegistrationException("Registration unsuccessful: username already exists");
        }
    }

    @Override
    public String getUsername(int userId) {
        User user = userRepository.findOneByUserId(userId);
        return user.getUsername();
    }

    @Override
    public ReturnDTO deleteUser(HttpServletRequest request) {
        if (!SessionData.validateSession(request)) {
            return ReturnConstants.badSession();
        }

        try {
            int uid = (int) request.getSession().getAttribute("USER_ID");
            User user = userRepository.findOneByUserId(uid);
            userRepository.delete(user);
            return ReturnConstants.simpleSuccess("Success: Account deleted", uid);
        }
        // TODO figure out what the two exceptions are (one for username not found and
        // one for failed delete, and handle errors separately)
        catch (Exception e) {
            return ReturnConstants.unknownError("Error: Delete failed");
        }
    }

    @Override
    public int loginUser(LoginDTO loginDTO) throws Exception {
        User user = userRepository.findByUsername(loginDTO.getUsername());

        if (user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return user.getUserId();
        } else {
            throw new InvalidLoginException();
        }
    }

    @Override
    public ResponseEntity<String> changePassword(ChangePWDTO changePWDTO, HttpServletRequest request) {
        User user = userRepository.findOneByUserId((int) request.getSession().getAttribute("USER_ID"));
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
    public ReturnDTO getUserObject(HttpServletRequest request) {
        ReturnDTO returnDTO;

        User user = userRepository.findOneByUserId(SessionData.getUserId(request));

        ReturnBuilder builder = new ReturnBuilder();
        returnDTO = builder
                .withUsername(user.getUsername())
                .withFirstname(user.getFirstName())
                .withLastname(user.getLastName())
                .withEmail(user.getEmail())
                .build();

        return returnDTO;
    }

    @Override
    public ResponseEntity<String> changeEmail(String email, HttpServletRequest request) {
        User user = userRepository.findOneByUserId((int) request.getSession().getAttribute("USER_ID"));
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
                validName(userDTO.getLastname()) &&
                validEmail(userDTO.getEmail())) {
            return true;
        }
        return false;
    }

    private boolean validEmail(String email) {
        try {
            return org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(email);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validUsername(String username) {
        return (username.length() >= 4) && (username.length() < 20);
    }

    private boolean validName(String name) {
        return (name.length() >= 2) && (name.length() <= 15);
    }

    private boolean validPassword(String pw) {
        if (pw.length() < 8 || pw.length() >= 20) {
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
        for (int i = 0; i < pw.length(); i++) {
            if (pw.charAt(i) >= 65 && pw.charAt(i) <= 90) {
                return true;
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
