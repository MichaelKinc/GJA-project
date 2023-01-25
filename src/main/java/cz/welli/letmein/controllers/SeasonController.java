package cz.welli.letmein.controllers;

import cz.welli.letmein.models.Place;
import cz.welli.letmein.models.Season;
import cz.welli.letmein.repositories.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SeasonController {

    @Autowired
    private SeasonRepository seasonRepository;

    @GetMapping("/admin/seasons")
    public ModelAndView showActivities() {
        ModelAndView mav = new ModelAndView("seasons");
        List<Season> seasons = seasonRepository.findAll();
        mav.addObject("seasons", seasons);
        return mav;
    }

    @GetMapping("/admin/seasons/create")
    public ModelAndView showAddActivityForm() {
        ModelAndView mav = new ModelAndView("add-season-form");
        Season season = new Season();
        mav.addObject("place", season);
        return mav;
    }

    @PostMapping("/admin/seasons/create")
    public String savePlace(@ModelAttribute Season season, Model model) {
        if(season.getStart().compareTo(season.getEnd()) > 0) {
            model.addAttribute("errorMsg", "Konec sezóny nesmí být dříve než její začátek!");
            return "add-season-form";
        }
        seasonRepository.save(season);
        return "redirect:/admin/seasons";
    }
}
