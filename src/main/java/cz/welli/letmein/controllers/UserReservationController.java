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

import java.time.LocalDate;
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

    private Activity selectedActivity;
    private Place selectedPlace;
    private LocalDate selectedDate;

    private int getProgressStep() {
        int step = 1;
        if (selectedActivity != null) {
            step = 2;
            if (selectedPlace != null) {
                step = 3;
                if (selectedDate != null) {
                    step = 4;
                }
            }
        }
        return step;
    }

    private void removeOneStep() {
        switch (getProgressStep()) {
            case 2 -> selectedActivity = null;
            case 3 -> selectedPlace = null;
            case 4 -> selectedDate = null;
            default -> {
            }
        }
    }

    @GetMapping("/user/reservations")
    public String getReservations (Model model) {
        return "reservations";
    }

    @GetMapping("/user/reservations/create")
    public String getReservationCreateForm (Model model,
                                     @ModelAttribute("reservation") UserReservationForm reservation) {

        System.out.println("GET: Hello");

        if (getProgressStep() == 1) {
            List<Activity> activities = activityRepository.findAll();
            model.addAttribute("activities", activities);
        }

        if (getProgressStep() == 2) {
            Set<Place> places = selectedActivity.getPlaces();
            model.addAttribute("places", places);
        }

        model.addAttribute("step", getProgressStep());
        return "reservations-create";
    }
    @PostMapping("/user/reservations/create")
    public String createReservation (Model model,
                                @ModelAttribute("reservation") UserReservationForm reservation,
                                BindingResult result,
                                @RequestParam(value="action", required=true) String action) {
        if (result.hasErrors()) {
            System.out.println(result.toString());
            return "error";
        }

        if (action.equals("back")) {
            System.out.println("Before step: " + getProgressStep());
            removeOneStep();
            System.out.println("Actual step: " + getProgressStep());
            model.addAttribute("step", getProgressStep());
            return "redirect:/user/reservations/create";
        }

        if (reservation.getActivity() != null) {
            selectedActivity = reservation.getActivity();
        }
        if (reservation.getPlace() != null) {
            selectedPlace = reservation.getPlace();
        }
        if (reservation.getDate() != null) {
            selectedDate = reservation.getDate();
        }

        if (getProgressStep() == 1) {
            List<Activity> activities = activityRepository.findAll();
            model.addAttribute("activities", activities);
        }

        if (getProgressStep() == 2) {
            Set<Place> places = selectedActivity.getPlaces();
            model.addAttribute("places", places);
        }

        model.addAttribute("step", getProgressStep());
        return "reservations-create";
    }

    /*@PostMapping("/user/reservations/create/step-back")
    public String stepBack (Model model,
                                     @ModelAttribute("reservation") UserReservationForm reservation,
                                     BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.toString());
            return "error";
        }

        if (reservation.getSubmit() != null && reservation.getSubmit().equals("back")) {
            System.out.println("Remove 1 step");
            removeOneStep();
            model.addAttribute("step", getProgressStep());
            return "reservations-create";
        }
    }*/
}
