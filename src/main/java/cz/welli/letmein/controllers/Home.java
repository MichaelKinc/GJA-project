package cz.welli.letmein.controllers;

import cz.welli.letmein.models.Reservation;
import cz.welli.letmein.models.User;
import cz.welli.letmein.repositories.ReservationRepository;
import cz.welli.letmein.repositories.UserRepository;
import cz.welli.letmein.services.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class Home {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/home")
    public String setupHome(Model model) {
        String activeUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User activeUser = userRepository.findByEmail(activeUserEmail);
        List<Reservation> upcommingReservations = reservationRepository.findUpcomming(activeUser, LocalDateTime.now());

        model.addAttribute("upcommingReservations", upcommingReservations);
        return "home";
    }

}
