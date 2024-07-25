package com.prashanth.expense.controller;

import com.authy.AuthyApiClient;
import com.authy.AuthyException;
import com.authy.api.User;
import com.authy.api.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private AuthyApiClient authyApiClient;

//    @GetMapping("/register")
//    public String showRegistrationForm() {
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String registerUser(@RequestParam String email, @RequestParam String phone, Model model) {
//        Users users = authyApiClient.getUsers();
//        User user = users.n
//        user.create();
//
//        model.addAttribute("authyId", user.getId());
//        return "verify"; // Redirect to verification page
//    }

    @PostMapping("/verify")
    public String verifyUser(@RequestParam String token, @RequestParam int authyId, Model model) throws AuthyException {
        boolean isValid = authyApiClient.getTokens().verify(authyId, token).isOk();
        System.out.println(isValid);
        if (isValid) {
            // User is authenticated, redirect to a protected resource
            return "redirect:/home"; // Change this to your home page
        } else {
            model.addAttribute("error", "Invalid token. Please try again.");
            return "verify"; // Stay on the verification page
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, Model model) {
        // Verify user's email and password
        User user = userService.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // User is authenticated, initiate Authy 2FA
            Tokens tokens = authyApiClient.getTokens();
            Token tokenResponse = tokens.requestSms(user.getAuthyId());
            if (tokenResponse.isOk()) {
                model.addAttribute("authyId", user.getAuthyId());
                return "redirect:/verify"; // Redirect to verification page
            } else {
                model.addAttribute("error", "Failed to send verification code.");
                return "login"; // Stay on the login page
            }
        } else {
            model.addAttribute("error", "Invalid email or password.");
            return "login"; // Stay on the login page
        }
    }

    @GetMapping("/home")
    public String home() {
        return "home"; // Your protected home page
    }
}
