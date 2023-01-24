package cz.welli.letmein.controllers;

import cz.welli.letmein.models.Activity;
import cz.welli.letmein.models.Place;
import cz.welli.letmein.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlaceController {

    @Autowired
    private PlaceRepository placeRepository;

    @GetMapping("/add-place")
    public ModelAndView showAddActivityForm() {
        ModelAndView mav = new ModelAndView("add-place-form");
        Place place = new Place();
        mav.addObject("place", place);
        return mav;
    }

}
