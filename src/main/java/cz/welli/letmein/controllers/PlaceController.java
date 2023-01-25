package cz.welli.letmein.controllers;

import cz.welli.letmein.dto.AddPlaceForm;
import cz.welli.letmein.models.Activity;
import cz.welli.letmein.models.Place;
import cz.welli.letmein.models.Season;
import cz.welli.letmein.repositories.ActivityRepository;
import cz.welli.letmein.repositories.PlaceRepository;
import cz.welli.letmein.repositories.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class PlaceController {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping("/admin/places")
    public ModelAndView showActivities() {
        ModelAndView mav = new ModelAndView("places");
        List<Place> places = placeRepository.findAll();
        mav.addObject("places", places);
        return mav;
    }

    @GetMapping("/admin/places/create")
    public String showAddActivityForm(Model model) {
        List<Season> seasons = seasonRepository.findAll();
        AddPlaceForm place = new AddPlaceForm();
        List<Activity> activities = activityRepository.findAll();
        model.addAttribute("seasons", seasons);
        model.addAttribute("place", place);
        model.addAttribute("activities", activities);
        return "add-place-form";
    }

    @PostMapping("/admin/places/create")
    public String savePlace(@ModelAttribute AddPlaceForm place) {
        Place newPlace = new Place();
        newPlace.setCapacity(place.getCapacity());
        newPlace.setEnd(place.getEnd());
        newPlace.setStart(place.getStart());
        Set<Season> s = new HashSet<>(place.getSeasons());
        newPlace.setSeasons(s);
        newPlace.setName(place.getName());
        newPlace.setTimeWindow(place.getTimeWindow());
        newPlace.setActivity(place.getActivity());
        newPlace.getActivity().getPlaces().add(newPlace);
        placeRepository.save(newPlace);
        return "redirect:/admin/places";
    }

}
