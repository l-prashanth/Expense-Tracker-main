package com.prashanth.expense.controller;

import com.prashanth.expense.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class AuthController {


    private LoginService loginService;

    @GetMapping("/login")
    public String showLoginForm(Model model, HttpServletRequest request) {
        return loginService.loginPage(model,request);
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String password, Model model, HttpServletRequest request)  {
        return loginService.loginSession(password,model,request);
    }
}