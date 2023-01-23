package cz.welli.letmein.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Admin {
    @GetMapping("/home/admin")
    public String setuAdmin() {
        return "admin";
    }

}
