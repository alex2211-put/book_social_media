package ru.iliya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.iliya.security.SecurityUserConverter;

@Controller
@RequestMapping("/auth")
public class SecurityController {

    @Autowired
    SecurityUserConverter securityUserConverter;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/success")
    public String getSuccessPage( @AuthenticationPrincipal UserDetails currentUser,
                                  Model model) {
        model.addAttribute("user",
                securityUserConverter.getUserByDetails(currentUser));

        return "user";
    }
}
