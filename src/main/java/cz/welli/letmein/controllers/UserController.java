package cz.welli.letmein.controllers;

import cz.welli.letmein.dto.AddPlaceForm;
import cz.welli.letmein.dto.AddUserForm;
import cz.welli.letmein.models.*;
import cz.welli.letmein.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/users")
    public ModelAndView showUsers() {
        ModelAndView mav = new ModelAndView("users");
        List<User> users = userRepository.findAll();
        mav.addObject("users", users);
        return mav;
    }

    @GetMapping("/admin/users/create")
    public String showAddActivityForm(Model model) {
        AddUserForm user = new AddUserForm();
        model.addAttribute("user", user);
        return "add-user-form";
    }

    @PostMapping("/admin/users/create")
    public String saveUser(@ModelAttribute AddUserForm user) {
        System.out.println("novej");
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(encodedPassword);
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setUserRole(user.getUserRole());
        userRepository.save(newUser);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/users/update")
    public String updateUser(@ModelAttribute AddUserForm user) {
        Optional<User> newUser = userRepository.findById(user.getId());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        newUser.get().setPassword(encodedPassword);
        newUser.get().setFirstName(user.getFirstName());
        newUser.get().setLastName(user.getLastName());
        newUser.get().setUserRole(user.getUserRole());
        userRepository.save(newUser.get());
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/update")
    public ModelAndView showUpdateUserForm(@RequestParam Long userId) {
        ModelAndView mav = new ModelAndView("update-user-form");
        User userFromDb = userRepository.findById(userId).get();
        AddUserForm user = new AddUserForm();
        user.setId(userFromDb.getId());
        user.setFirstName(userFromDb.getFirstName());
        user.setLastName(userFromDb.getLastName());
        user.setUserRole(userFromDb.getUserRoleRaw());
        mav.addObject("user", user);
        return mav;
    }
}
