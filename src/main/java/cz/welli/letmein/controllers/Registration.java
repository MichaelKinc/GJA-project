package cz.welli.letmein.controllers;

import cz.welli.letmein.models.User;
import cz.welli.letmein.repositories.UserRepository;
import cz.welli.letmein.services.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Registration {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    EmailServiceImpl emailService;

    @GetMapping("/register")
    public String setupRegistration(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/process_register")
    public String processRegister(User user, RedirectAttributes redirectAttrs) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        User dbUser = userRepo.findByEmail(user.getEmail());
        if(dbUser != null) {
            redirectAttrs.addFlashAttribute("errorMsg", "E-mail je již asociován s existujícím účtem.");
            return "redirect:/register";
        }
        try {
            userRepo.save(user);
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("errorMsg", "Neočekáváná chyba při vytváření účtu." +
                    " Zkuste to prosím později");
            return "redirect:/register";
        }

        emailService.sendConfirmUserRegistration(user.getEmail(), user.getFullName(), user.getFirstName(),
                user.getLastName());
        return "redirect:/login?registration_success";
    }
}
