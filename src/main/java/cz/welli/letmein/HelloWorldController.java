package cz.welli.letmein;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping
    String hello() {
        return "Hello World, Spring Boot!";
    }
}
