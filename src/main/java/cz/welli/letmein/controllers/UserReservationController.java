package cz.welli.letmein.controllers;

import cz.welli.letmein.models.Activity;
import cz.welli.letmein.models.Place;
import cz.welli.letmein.models.UserReservationForm;
import cz.welli.letmein.repositories.ActivityRepository;
import cz.welli.letmein.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
public class UserReservationController {

    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    PlaceRepository placeRepository;

    @GetMapping("/user/reservations")
    String getReservations (Model model) {
        return "reservations";
    }

    @GetMapping("/user/reservations/create")
    String getReservationCreateForm (Model model,
                                     @ModelAttribute("reservation") UserReservationForm reservation) {

        List<Activity> activities = activityRepository.findAll();
        model.addAttribute("activities", activities);

        System.out.println("GET: Hello");

        return "reservations-create";
    }
    @PostMapping("/user/reservations/create")
    String createReservation (Model model,
                              @RequestParam(value = "step", required = false) String step,
                              @ModelAttribute("reservation") UserReservationForm reservation,
                              BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.toString());
            return "error";
        }

        if (reservation.currentStepAheadOfStep(1)) {
            model.addAttribute("selectedActivity", reservation.getActivity());
        }

        if (reservation.isStep(2)) {
            Set<Place> places = reservation.getActivity().getPlaces();
            model.addAttribute("places", places);
        }

        return "reservations-create";
    }
}
