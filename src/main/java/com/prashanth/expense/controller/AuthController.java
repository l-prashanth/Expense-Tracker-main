package com.prashanth.expense.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Value("${my.password}")
    private String hardcodedPassword; // This will be the password from GitHub Secrets

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Return the login view
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String password, Model model) {
        // Check against the hardcoded password
        System.out.println(password);
        if (hardcodedPassword.equals(password)) {
            // Successful login
            return "redirect:/budget"; // Redirect to home page
        } else {
            // Invalid credentials
            model.addAttribute("error", true);
            return "login"; // Stay on the login page
        }
    }
}