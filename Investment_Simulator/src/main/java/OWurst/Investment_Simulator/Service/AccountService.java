package OWurst.Investment_Simulator.Service;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import OWurst.Investment_Simulator.DTO.ChangePWDTO;
import OWurst.Investment_Simulator.DTO.ReturnDTO;

public interface AccountService {
    public ResponseEntity<String> getUsername(HttpServletRequest request);

    public ReturnDTO deleteUser(HttpServletRequest request);

    public ResponseEntity<String> changePassword(ChangePWDTO changePWDTO, HttpServletRequest request);

    public ResponseEntity<String> changeEmail(String email, HttpServletRequest request);

    public ReturnDTO getUserObject(HttpServletRequest request);
}