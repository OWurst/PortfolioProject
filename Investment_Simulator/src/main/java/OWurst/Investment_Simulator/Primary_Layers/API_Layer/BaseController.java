package OWurst.Investment_Simulator.Primary_Layers.API_Layer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @GetMapping("/")
    public String Howdy() {
        return "Hello Wrld";
    }
}
