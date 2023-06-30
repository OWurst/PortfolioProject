package OWurst.Investment_Simulator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PutMapping; // for update account
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import OWurst.Investment_Simulator.DTO.UserDTO; // will be used to update account
import OWurst.Investment_Simulator.Service.AccountService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AccountController {
    @Autowired
    AccountService accountService;

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(HttpServletRequest request) {
        return accountService.deleteUser(request);
    }
}