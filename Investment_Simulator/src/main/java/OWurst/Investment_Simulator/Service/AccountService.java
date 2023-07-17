package OWurst.Investment_Simulator.Service;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

public interface AccountService {
    public ResponseEntity<String> getUsername(HttpServletRequest request);

    public ResponseEntity<String> deleteUser(HttpServletRequest request);
}