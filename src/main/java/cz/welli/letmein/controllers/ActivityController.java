package cz.welli.letmein.controllers;

import cz.welli.letmein.models.Activity;
import cz.welli.letmein.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping("/admin/show-activities")
    public ModelAndView showActivities() {
        ModelAndView mav = new ModelAndView("activities");
        List<Activity> activities = activityRepository.findAll();
        mav.addObject("activities", activities);
        return mav;
    }

    @GetMapping("/admin/add-activity")
    public ModelAndView showAddActivityForm() {
        ModelAndView mav = new ModelAndView("add-activity-form");
        Activity activity = new Activity();
        mav.addObject("activity", activity);
        return mav;
    }

    @PostMapping("/admin/save-activity")
    public String saveActivity(@ModelAttribute Activity activity) {
        activityRepository.save(activity);
        System.out.println(activity.getType());
        return "redirect:/home/admin/show-activities";
    }
}
