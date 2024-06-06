package OWurst.Investment_Simulator.Service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import OWurst.Investment_Simulator.DTO.LoginDTO;
import OWurst.Investment_Simulator.DTO.UserDTO;

public interface AuthService {
    public int addUser(UserDTO userDTO) throws Exception;

    public ResponseEntity<String> loginUser(LoginDTO loginDTO, HttpServletRequest request);

    public ResponseEntity<String> logoutUser(HttpServletRequest request);
}
