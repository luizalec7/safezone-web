package com.safezone.web.controller;

import com.safezone.web.model.Usuario;
import com.safezone.web.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
    public String loginManual(@RequestParam String email, @RequestParam String password,
                              Model model, HttpSession session) {
        try {
            Usuario usuario = usuarioService.buscarPorEmail(email)
                    .orElseThrow(() -> new Exception("Usuário não encontrado"));

            if (passwordEncoder.matches(password, usuario.getSenha())) {
                session.setAttribute("usuarioId", usuario.getId()); // 👈 Salva na sessão
                return "redirect:/home";
            } else {
                model.addAttribute("error", "Senha inválida");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Usuário não encontrado");
        }
        return "login";
    }
}