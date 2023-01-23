package cz.welli.letmein.controllers;

import cz.welli.letmein.models.User;
import cz.welli.letmein.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLIntegrityConstraintViolationException;

@Controller
public class Registration {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/register")
    public String setupRegistration(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }


    @PostMapping("/process_register")
    public String processRegister(User user, Model model) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);

        try {
            userRepo.save(user);
        } catch (Exception e) {
            model.addAttribute("errorMsg", "Ups, this email, already exist");
            return "registration";
        }

        model.addAttribute("errorMsg", "");
        return "register_success";
    }
}
