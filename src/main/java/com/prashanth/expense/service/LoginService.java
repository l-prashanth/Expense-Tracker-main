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

    public String loginSession(String password, Model model) {
        if (authenticate(password)) {
            openSession();
            return "redirect:/budget";
        } else {
            model.addAttribute("error", true);
            return "login"; // Stay on the login page
        }
    }
    public void openSession() {
        Login login = new Login();
        login.setUser("Personal");
        loginRepository.save(login);
    }
    public void closeSession() {
        Login login=new Login();
        login.setUser("required");
        loginRepository.save(login);
    }
    public String budgetLogin(HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent");
        boolean isMobile = userAgent != null && userAgent.toLowerCase().contains("mobile");
        Optional<Login> user = loginRepository.findById(1);
        if (user.isPresent() && !Objects.equals(user.get().getUser(), "Personal")) {
            return "redirect:/login";
        }
        closeSession();
        if (isMobile) {
            return "mobileBudget"; // Return mobile-specific HTML
        } else {
            return "budget"; // Return desktop HTML
        }
    }
}
