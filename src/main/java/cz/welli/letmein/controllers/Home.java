package cz.welli.letmein.controllers;

import cz.welli.letmein.services.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {

    @Autowired
    EmailServiceImpl es;
    @GetMapping("/home")
    public String setupHome() {
        return "home";
    }

}
