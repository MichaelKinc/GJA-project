package cz.welli.letmein.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cz.welli.letmein.entity.PinCodeFormResponse;

@Controller
public class KioskController {
    @GetMapping("/kiosk")
    String getKiosk (Model model) {
        model.addAttribute("uploaded", false);
        return "kiosk";
    }

    @PostMapping("/kiosk")
    String verifyKioskCode (PinCodeFormResponse pin, Model model) {
        if (pin.isValid()) {
            //Validation
            if (pin.comparePin("111111")) {
                model.addAttribute("verification_result", "success");
            } else {
                model.addAttribute("verification_result", "fail");
            }
        }
        model.addAttribute("uploaded", true);
        return "kiosk";
    }
}
