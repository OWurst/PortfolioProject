package OWurst.Investment_Simulator.Primary_Layers.API_Layer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class BaseController {
    @GetMapping("/spring")
    public ResponseEntity<String> Howdy() {
        return new ResponseEntity<String>("{\"msg\":\"Hello From Spring\"}", null, 200);
    }
}
