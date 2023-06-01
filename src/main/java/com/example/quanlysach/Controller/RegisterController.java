package com.example.quanlysach.Controller;

import com.example.quanlysach.Model.User;
import com.example.quanlysach.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService userService;
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(Model model, @ModelAttribute("user") User u) {
        if (userService.isUserExist(u.getEmail()) || userService.getUserByString(u.getUsername()) != null) {
            model.addAttribute("user", u);
            return "user/register";
        }
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        userService.NewUser(u);
        return "redirect:/login";
    }
}
