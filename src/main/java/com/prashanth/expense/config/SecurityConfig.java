//package com.prashanth.expense.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // Use BCrypt for password hashing
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/", "/login,register,verify").permitAll() // Allow access to login and home
//                        .requestMatchers("/budget").hasRole("USER") // Restrict access to /budget to users with USER role
//                        .anyRequest().authenticated() // All other requests require authentication
//                )
//                .formLogin(form -> form
//                        .loginPage("/login") // Custom login page
//                        .permitAll()
//                )
//                .logout(LogoutConfigurer::permitAll
//                );
//
//        return http.build();
//    }
//}
//
