package cz.welli.letmein.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserReservationController {
    @GetMapping("/user/reservations")
    String getReservations (Model model) {
        return "reservations";
    }

    @GetMapping("/user/reservations/create")
    String getReservationCreateForm (Model model, @RequestParam(value = "step", required = false) String step) {
        model.addAttribute("step", step);
        return "reservations-create";
    }
    @PostMapping("/user/reservations/create")
    String createReservation (Model model, @RequestParam(value = "step", required = false) String step) {
        System.out.println(step);
        return "reservations-create";
    }
}
