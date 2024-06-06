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
import OWurst.Investment_Simulator.Utils.ReturnConstants;
import OWurst.Investment_Simulator.DTO.ReturnDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = AddressConstants.FRONTEND_URL, allowCredentials = "true")
@RequestMapping("/core/user")
public class AccountController {
    @Autowired
    AccountService accountService;

    @DeleteMapping("/delete")
    public ResponseEntity<ReturnDTO> deleteUser(HttpServletRequest request) {
        ReturnDTO returnDTO = accountService.deleteUser(request);

        return ResponseEntity.status(returnDTO.getRespCode()).body(returnDTO);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody ChangePWDTO passchangeDTO, HttpServletRequest request) {
        return accountService.changePassword(passchangeDTO, request);
    }

    @GetMapping("/user_object")
    public ResponseEntity<ReturnDTO> getUserObject(HttpServletRequest request) {
        ReturnDTO returnDTO = accountService.getUserObject(request);

        return ResponseEntity.status(returnDTO.getRespCode()).body(returnDTO);
    }

    @PutMapping("/updateEmail")
    public ResponseEntity<String> updateEmail(@RequestParam String email, HttpServletRequest request) {
        return accountService.changeEmail(email, request);
    }

    @GetMapping("/get-username")
    public ResponseEntity<ReturnDTO> getUsername(HttpServletRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest().body(ReturnConstants.badSession());
        }

        int uid;
        try {
            uid = (int) request.getSession().getAttribute("USER_ID");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ReturnConstants.unverifiedSession());
        }

        try {
            return ResponseEntity.ok().body(ReturnConstants.simpleSuccess(accountService.getUsername(uid), uid));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ReturnConstants.unknownError(e.getMessage()));
        }
    }
}