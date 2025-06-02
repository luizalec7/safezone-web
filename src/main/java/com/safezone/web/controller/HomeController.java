package com.safezone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("user", principal);
        }
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/perfil")
    public String perfil(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("user", principal);
        }
        return "perfil";
    }
}