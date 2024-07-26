package com.prashanth.expense.controller;

import com.prashanth.expense.repository.LoginRepository;
import com.prashanth.expense.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class AuthController {


    private final LoginRepository loginRepository;
    private LoginService loginService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("error", false);
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String password, Model model)  {
        return loginService.loginSession(password,model);
    }
}