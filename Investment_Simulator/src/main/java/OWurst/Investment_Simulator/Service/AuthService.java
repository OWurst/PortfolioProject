package OWurst.Investment_Simulator.Service;

import OWurst.Investment_Simulator.DTO.LoginDTO;
import OWurst.Investment_Simulator.DTO.UserDTO;

public interface AuthService {
    public int addUser(UserDTO userDTO) throws Exception;

    public int loginUser(LoginDTO loginDTO) throws Exception;
}
