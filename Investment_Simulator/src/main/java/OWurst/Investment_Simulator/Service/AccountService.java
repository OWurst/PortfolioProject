package OWurst.Investment_Simulator.Service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

//import OWurst.Investment_Simulator.DTO.UserDTO; //will be used for update user down the road

public interface AccountService {
    public ResponseEntity<String> getUsername(HttpServletRequest request);

    public ResponseEntity<String> deleteUser(HttpServletRequest request);
}