package OWurst.Investment_Simulator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import OWurst.Investment_Simulator.Constants.AddressConstants;
import OWurst.Investment_Simulator.DTO.ChangePWDTO;
import OWurst.Investment_Simulator.Service.AccountService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = AddressConstants.FRONTEND_URL, allowCredentials = "true")
@RequestMapping("/core/user")
public class AccountController {
    @Autowired
    AccountService accountService;

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(HttpServletRequest request) {
        return accountService.deleteUser(request);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody ChangePWDTO passchangeDTO, HttpServletRequest request) {
        return accountService.changePassword(passchangeDTO, request);
    }

    @GetMapping("/user_object")
    public ResponseEntity<String> getUserObject(HttpServletRequest request) {
        return accountService.getUserObject(request);
    }

    @PutMapping("/updateEmail")
    public ResponseEntity<String> updateEmail(@RequestParam String email, HttpServletRequest request) {
        return accountService.changeEmail(email, request);
    }
}