package cz.welli.letmein.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class HelloController {

    @GetMapping("/redirect")
    public RedirectView redirectWithUsingRedirectView(
            RedirectAttributes attributes) {
//        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
//        attributes.addAttribute("attribute", "redirectWithRedirectView");
        return new RedirectView("/home");
    }

//    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
//    public ModelAndView welcomePage() {
//
//        ModelAndView model = new ModelAndView();
//        model.addObject("title", "Spring Security Custom Login Form");
//        model.addObject("message", "This is welcome page!");
//        model.setViewName("hello");
//        return model;
//
//    }
//
//    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
//    public ModelAndView adminPage() {
//
//        ModelAndView model = new ModelAndView();
//        model.addObject("title", "Spring Security Custom Login Form");
//        model.addObject("message", "This is protected page!");
//        model.setViewName("admin");
//
//        return model;
//
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
//                              @RequestParam(value = "logout", required = false) String logout) {
//
//        ModelAndView model = new ModelAndView();
//        if (error != null) {
//            model.addObject("error", "Invalid username and password!");
//        }
//
//        if (logout != null) {
//            model.addObject("logout", "You've been logged out successfully.");
//        }
//        model.setViewName("login");
//
//        return model;
//    }

}
