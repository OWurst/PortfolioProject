package OWurst.Investment_Simulator.Primary_Layers.Service_Layer;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import OWurst.Investment_Simulator.General_Objects.DTO.UserDTO;
import OWurst.Investment_Simulator.General_Objects.DTO.LoginDTO;

public interface UserService {
    public ResponseEntity<String> addUser(UserDTO userDTO, HttpServletRequest request);

    public ResponseEntity<String> getUsername(HttpServletRequest request);

    public ResponseEntity<String> loginUser(LoginDTO loginDTO, HttpServletRequest request);

    public ResponseEntity<String> logoutUser(HttpServletRequest request);
}
