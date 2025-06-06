package com.safezone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirecionarParaLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String exibirLogin() {
        return "login";
    }

    @GetMapping("/home")
    public String exibirHome(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("user", principal);
        }
        return "home";
    }

    @GetMapping("/perfil")
    public String exibirPerfil(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("user", principal);
        }
        return "profile";
    }
}