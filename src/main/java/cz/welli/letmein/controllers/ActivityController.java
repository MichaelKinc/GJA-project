package cz.welli.letmein.controllers;

import cz.welli.letmein.models.Activity;
import cz.welli.letmein.models.Place;
import cz.welli.letmein.repositories.ActivityRepository;
import cz.welli.letmein.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

/**
 * Activity view controller
 */
@Controller
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private PlaceRepository placeRepository;

    /**
     * Activities table view
     * @return Model and view of activities
     */
    @GetMapping("/admin/activities")
    public ModelAndView showActivities() {
        ModelAndView mav = new ModelAndView("activities");
        List<Activity> activities = activityRepository.findAll();
        mav.addObject("activities", activities);
        return mav;
    }

    /**
     * Activities create view
     * @return Model and view of activity detail
     */
    @GetMapping("/admin/activities/create")
    public ModelAndView showAddActivityForm() {
        ModelAndView mav = new ModelAndView("add-activity-form");
        Activity activity = new Activity();
        mav.addObject("activity", activity);
        return mav;
    }

    /**
     * Update activity view
     * @param activityId id of activity
     * @return Model and view of activity detail
     */
    @GetMapping("/admin/activities/update")
    public ModelAndView showUpdateActivityForm(@RequestParam Long activityId) {
        ModelAndView mav = new ModelAndView("add-activity-form");
        Activity activity = activityRepository.findById(activityId).get();
        mav.addObject("activity", activity);
        return mav;
    }

    /**
     * Delete activity route
     * @param activityId id of activity
     * @return redirect to admin/activities
     */
    @GetMapping("/admin/activities/delete")
    public String deleteActivity(@RequestParam Long activityId) {
        Optional<Activity> activity = activityRepository.findById(activityId);
        if(activity.isPresent()) {
            List<Place> places = placeRepository.findByActivity(activity.get());
            placeRepository.deleteAll(places);
        }
        activityRepository.deleteById(activityId);
        return "redirect:/admin/activities";
    }


    /**
     * Create activity route
     * @param activity id of activity
     * @return redirect to admin/activities
     */
    @PostMapping("/admin/activities/create")
    public String saveActivity(@ModelAttribute Activity activity) {
        activityRepository.save(activity);
        return "redirect:/admin/activities";
    }
}
