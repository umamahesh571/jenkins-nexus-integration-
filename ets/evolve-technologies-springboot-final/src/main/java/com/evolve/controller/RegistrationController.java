package com.evolve.controller;

import com.evolve.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        if (user.getName().isEmpty() || user.getEmail().isEmpty() || user.getContact().isEmpty()) {
            model.addAttribute("error", "All fields are required.");
            return "register";
        }
        model.addAttribute("message", "Thank you for registering, " + user.getName() + "!");
        return "success";
    }
}