package cz.welli.letmein.controllers;

import cz.welli.letmein.models.Reservation;
import cz.welli.letmein.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cz.welli.letmein.models.PinCodeFormResponse;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Kiosk view controller
 */
@Controller
public class KioskController {
    @Autowired
    ReservationRepository reservationRepository;

    /**
     * Get kiosk view
     * @param model
     * @return kiosk view file name
     */
    @GetMapping("/kiosk")
    String getKiosk (Model model) {
        model.addAttribute("uploaded", false);
        return "kiosk";
    }

    /**
     * Verify pin code method
     *
     * @param pin pin given by user
     * @param model model
     * @return kiosk view
     */
    @PostMapping("/kiosk")
    String verifyKioskCode (PinCodeFormResponse pin, Model model) {
        if (pin.isValid()) {
            //Validation
            List<Reservation> reservationsWithPin = reservationRepository.findByPin(pin.getPin());

            if (reservationsWithPin.size() > 1) {
                return "kiosk";
            }

            if (!reservationsWithPin.isEmpty()) {
                if (LocalDateTime.now().isAfter(reservationsWithPin.get(0).getStart()) && LocalDateTime.now().isBefore(reservationsWithPin.get(0).getStart().plusMinutes(59))) {
                    model.addAttribute("verification_result", "success");
                }
            } else {
                model.addAttribute("verification_result", "fail");
            }
        }
        model.addAttribute("uploaded", true);
        return "kiosk";
    }
}
