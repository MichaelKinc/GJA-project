package cz.welli.letmein.controllers;

import cz.welli.letmein.models.User;
import cz.welli.letmein.models.UserRole;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
@RequestMapping("/")
public class Errore implements ErrorController {

//    @GetMapping("/error")
//    public RedirectView setupError(RedirectAttributes attributes) {
////            attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
////            attributes.addAttribute("attribute", "redirectWithRedirectView");
//            return new RedirectView("home");
//        }


    /**
     * Supports the HTML Error View
        //todo
     * @return
     */
    @RequestMapping(value = "/error", produces = "text/html")
    public ModelAndView errorHtml(Authentication authentication, ModelMap model) {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String viewName;
        if(roles.contains("ROLE_KIOSK")) {
            viewName = "redirect:/kiosk";
        } else {
            viewName = "redirect:/home";
        }
            return new ModelAndView(viewName, model);
    }
}
