package cz.welli.letmein.controllers;

import cz.welli.letmein.dto.ReservationSlotDTO;
import cz.welli.letmein.models.*;
import cz.welli.letmein.repositories.ActivityRepository;
import cz.welli.letmein.repositories.PlaceRepository;
import cz.welli.letmein.repositories.ReservationRepository;
import cz.welli.letmein.repositories.UserRepository;
import cz.welli.letmein.services.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
public class UserReservationController {
    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private EmailServiceImpl emailService;

    private Activity selectedActivity;
    private Place selectedPlace;
    private LocalDate selectedDate;
    private LocalDateTime selectedSlotDateTime;
    private int selectedCapacity;

    private int getProgressStep() {
        int step = 1;
        if (selectedActivity != null) {
            step = 2;
            if (selectedPlace != null) {
                step = 3;
                if (selectedDate != null) {
                    step = 4;
                    if (selectedSlotDateTime != null) {
                        step = 5;
                    }
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
            case 5 -> selectedSlotDateTime = null;
            default -> {
            }
        }
    }

    private void removeAllStoredData() {
        selectedActivity = null;
        selectedPlace = null;
        selectedDate = null;
        selectedSlotDateTime = null;
        selectedCapacity = 0;
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

        if (getProgressStep() == 4) {
            int maxCapacity = selectedPlace.getCapacity();
            int availableCapacity = selectedPlace.getCapacity();
            int hoursStep = selectedPlace.getTimeWindow();
            LocalTime start = selectedPlace.getStart();
            LocalTime end = selectedPlace.getEnd();
            List<ReservationSlotDTO> generatedSlots = new ArrayList<>();

            while (start != end) {
                LocalDateTime composedDateTime = selectedDate.atTime(start);

                System.out.println("Composed LocalDateTime: " + composedDateTime);

                List<Reservation> existingReservations = reservationRepository.findByDateTime(selectedPlace, composedDateTime);

                System.out.println("Start: " + start + ", # of existing reservations: " + existingReservations.size());

                if (!existingReservations.isEmpty()) {
                    for (Reservation existingRes: existingReservations
                    ) {
                        availableCapacity -= existingRes.getPersons();
                    }
                }

                ReservationSlotDTO newSlot = new ReservationSlotDTO(
                        start,
                        start.plusHours(hoursStep),
                        availableCapacity,
                        maxCapacity
                );

                generatedSlots.add(newSlot);
                System.out.println(newSlot);
                // Increment to next slot
                start = start.plusHours(hoursStep);
            }

            model.addAttribute("slots", generatedSlots);
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
        if (reservation.getStart() != null) {
            selectedSlotDateTime = selectedDate.atTime(reservation.getStart());
        }
        if (reservation.getRequiredCapacity() != 0) {
            selectedCapacity = reservation.getRequiredCapacity();
        }

        if (getProgressStep() == 1) {
            List<Activity> activities = activityRepository.findAll();
            model.addAttribute("activities", activities);
        }

        if (getProgressStep() == 2) {
            Set<Place> places = selectedActivity.getPlaces();
            model.addAttribute("places", places);
        }

        if (getProgressStep() == 4) {
            int maxCapacity = selectedPlace.getCapacity();
            int availableCapacity = selectedPlace.getCapacity();
            int hoursStep = selectedPlace.getTimeWindow();
            LocalTime start = selectedPlace.getStart();
            LocalTime end = selectedPlace.getEnd();
            List<ReservationSlotDTO> generatedSlots = new ArrayList<>();

            while (start != end) {
                LocalDateTime composedDateTime = selectedDate.atTime(start);

                System.out.println("Composed LocalDateTime: " + composedDateTime);

                List<Reservation> existingReservations = reservationRepository.findByDateTime(selectedPlace, composedDateTime);

                System.out.println("Start: " + start + ", # of existing reservations: " + existingReservations.size());

                if (!existingReservations.isEmpty()) {
                    for (Reservation existingRes: existingReservations
                    ) {
                        availableCapacity -= existingRes.getPersons();
                    }
                }

                ReservationSlotDTO newSlot = new ReservationSlotDTO(
                        start,
                        start.plusHours(hoursStep),
                        availableCapacity,
                        maxCapacity
                );

                generatedSlots.add(newSlot);
                System.out.println(newSlot);
                // Increment to next slot
                start = start.plusHours(hoursStep);
            }

            model.addAttribute("slots", generatedSlots);
        }

        if (getProgressStep() == 5) {
            if (reservation.isConfirmation()) {
                System.out.println("Create Reservation object in DB");

                Reservation newReservation = new Reservation();
                newReservation.setStart(selectedSlotDateTime);
                newReservation.setEnd(selectedSlotDateTime.plusHours(selectedPlace.getTimeWindow()));
                newReservation.setPersons(selectedCapacity);
                String activeUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
                User activeUser = userRepository.findByEmail(activeUserEmail);
                newReservation.setUser(activeUser);
                newReservation.setPlace(selectedPlace);

                /* Generate pin */
                int generatedValue = (new Random()).nextInt(900000) + 100000;
                String pin = String.valueOf(generatedValue);

                while (!reservationRepository.findByPin(pin).isEmpty()) {
                    generatedValue = (new Random()).nextInt(900000) + 100000;
                    pin = String.valueOf(generatedValue);
                }
                newReservation.setPin(pin);

                Reservation createdRes = reservationRepository.save(newReservation);

                /* Send PIN via email */
                emailService.sendConfirmEventRegistration(
                        activeUser.getEmail(),
                        createdRes.getId(),
                        activeUser.getFullName(),
                        createdRes.getPin(),
                        createdRes.getStart().toLocalDate().toString(),
                        createdRes.getPlace().getActivity().getType(),
                        createdRes.getPlace().getName(),
                        createdRes.getPersons()
                );

                removeAllStoredData();
                return "redirect:/user/reservations";
            }

            model.addAttribute("selectedActivity", selectedActivity);
            model.addAttribute("selectedPlace", selectedPlace);
            model.addAttribute("selectedDate", selectedDate);
            model.addAttribute("selectedSlot", selectedSlotDateTime);
            model.addAttribute("selectedCapacity", selectedCapacity);
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
