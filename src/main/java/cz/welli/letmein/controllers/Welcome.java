package cz.welli.letmein.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Welcome {
    @GetMapping("/")
    public String setupHome() {
        return "welcome";
    }
}
