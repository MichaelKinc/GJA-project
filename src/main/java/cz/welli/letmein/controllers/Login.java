package cz.welli.letmein.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import  org.springframework.security.core.Authentication;

/**
 * Class responsible for requests for /login route
 */
@Controller
public class Login {

    /**
     * Method for processing get request at route /login
     *
     * @param model model
     * @param error if not null, error message is exposed
     * @param logout if not null, logout message is exposed
     * @return redirect to /home if is user authenticated, login view otherwise
     */
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        if(authenticated()){
            return "redirect:/home";
        }

        return "login";
    }


    /**
     * Determines if a user is already authenticated.
     * @return True if is request authenticated
     */
    private boolean authenticated() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);
    }
}
