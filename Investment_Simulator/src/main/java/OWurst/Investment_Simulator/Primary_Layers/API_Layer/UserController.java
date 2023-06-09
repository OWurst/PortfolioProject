package OWurst.Investment_Simulator.Primary_Layers.API_Layer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping("/login")
    public ResponseEntity<String> loginUser() {
        return null;
    }
}