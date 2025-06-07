package com.safezone.web.controller;

import com.safezone.web.model.Usuario;
import com.safezone.web.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login/manual")
    public String loginManual(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            Usuario usuario = usuarioService.buscarPorEmail(email).orElseThrow(() -> new Exception("Usuário não encontrado"));
            if (passwordEncoder.matches(password, usuario.getSenha())) {
                // Login bem-sucedido
                return "redirect:/home";  // Redireciona para a home após o login
            } else {
                model.addAttribute("error", "Senha inválida");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Usuário não encontrado");
        }
        return "login"; // Retorna à página de login com mensagem de erro
    }
}