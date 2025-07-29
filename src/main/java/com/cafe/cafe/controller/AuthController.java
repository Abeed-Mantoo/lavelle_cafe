package com.cafe.cafe.controller;

import com.cafe.cafe.model.User;
import com.cafe.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")  // allow Angular frontend
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            return "Email already registered";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepo.save(user);
        return "Signup successful";
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginData) {
        Optional<User> userOpt = userRepo.findByEmail(loginData.getEmail());
        if (userOpt.isEmpty()) return "Invalid credentials";

        User user = userOpt.get();
        if (passwordEncoder.matches(loginData.getPassword(), user.getPassword())) {
            return user.getRole();  // Return role: "USER" or "ADMIN"
        } else {
            return "Invalid credentials";
        }
//        System.out.println("Login attempt for email: " + loginData.getEmail());
//        System.out.println("Password provided: " + loginData.getPassword());
//
//        if (userOpt.isEmpty()) {
//            System.out.println("❌ User not found.");
//            return "Invalid credentials";
//        }
//
//        User user = userOpt.get();
//        System.out.println("✅ Found user with role: " + user.getRole());
//        System.out.println("Stored hashed password: " + user.getPassword());
//
//        if (passwordEncoder.matches(loginData.getPassword(), user.getPassword())) {
//            System.out.println("✅ Password matched.");
//            return user.getRole();
//        } else {
//            System.out.println("❌ Password mismatch.");
//            return "Invalid credentials";
//        }

    }
}
