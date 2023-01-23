package cz.welli.letmein.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Kiosk {
    @GetMapping("/kiosk")
    public String setupKiosk() {
        return "kiosk";
    }
}
