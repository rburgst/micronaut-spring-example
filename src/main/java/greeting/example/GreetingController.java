package greeting.example;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;

@RestController
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greet")
    public Greeting greet(@RequestParam(value="tenantId") TenantId tenantId) {
        return greetingService.greeting(tenantId.getId().toString());
    }

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") @Pattern(regexp = "\\D+") String name) {
        return greetingService.greeting(name);
    }

    @PostMapping("/greeting")
    public Greeting greetingByPost(@RequestBody Greeting greeting) {
        return greetingService.greeting(greeting.getContent());
    }

    @DeleteMapping("/greeting")
    @Hidden
    public ResponseEntity<?> deleteGreeting() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Foo", "Bar");
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }


    @GetMapping("/greeting-status")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Greeting greetingWithStatus(@RequestParam(value="name", defaultValue="World") @Pattern(regexp = "\\D+") String name) {
        return greetingService.greeting(name);
    }

    @GetMapping(path = "/", produces = "text/html")
    public String home(Model model) {
        model.addAttribute(
                "message",
                "Welcome to Micronaut for Spring");

        return "home";
    }    
}
