package OWurst.Investment_Simulator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    @GetMapping("/")
    public String Howdy() {
        return "Hello World";
    }
}
