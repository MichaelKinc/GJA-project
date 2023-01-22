package cz.welli.letmein.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class Admin {
    @GetMapping("/home/admin")
    public String setupHome() {
        return "admin";
    }

}
