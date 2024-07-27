package com.prashanth.expense.service;

import com.prashanth.expense.model.Login;
import com.prashanth.expense.repository.LoginRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    @Value("${my.password}")
    private String loginPassword; // This will be the password from GitHub Secrets
    private final LoginRepository loginRepository;


    public boolean authenticate(String password) {
        return loginPassword.equals(password);
    }

    public String loginSession(String password, Model model, HttpServletRequest request) {
        if (authenticate(password)) {
            openSession();
            return "redirect:/budget";
        } else {
            model.addAttribute("error", true);
            if (isMobile(request)) {
                return "login"; // Stay on the login page

            } else {
                return "desktopLogin"; // Stay on the
            }
        }
    }

    public String loginPage(Model model, HttpServletRequest request) {
        model.addAttribute("error", false);
        if (isMobile(request)) {
            return "login"; // Stay on the login page

        } else {
            return "desktopLogin"; // Stay on the
        }
    }


    public void openSession() {
        Login login = new Login();
        login.setUser("Personal");
        loginRepository.save(login);
    }

    public void closeSession() {
        Login login = new Login();
        login.setUser("required");
        loginRepository.save(login);
    }

    public boolean isMobile(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return userAgent != null && userAgent.toLowerCase().contains("mobile");
    }

    public String budgetLogin(HttpServletRequest request) {
//
//        Optional<Login> user = loginRepository.findById(1);
//        if (user.isPresent() && !Objects.equals(user.get().getUser(), "Personal")) {
//            return "redirect:/login";
//        }
        closeSession();
        if (isMobile(request)) {
            return "mobileBudget"; // Return mobile-specific HTML
        } else {
            return "budget"; // Return desktop HTML
        }
    }
}
