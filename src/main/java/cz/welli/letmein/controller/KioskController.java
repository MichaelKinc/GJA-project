package cz.welli.letmein.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KioskController {
    @GetMapping("/kiosk")
    String getKiosk (Model model) {
        model.addAttribute("uploaded", false);
        model.addAttribute("verification_result", "fail");
        return "kiosk";
    }


}
