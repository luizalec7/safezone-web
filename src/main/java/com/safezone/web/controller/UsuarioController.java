package com.safezone.web.controller;

import com.safezone.web.model.Usuario;
import com.safezone.web.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String exibirFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    @PostMapping("/register")
    public String registrarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario,
                                   BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        if (usuarioService.buscarPorEmail(usuario.getEmail()).isPresent()) {
            model.addAttribute("erro", "Email já está em uso.");
            return "register";
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioService.salvar(usuario);
        return "redirect:/login?cadastro=sucesso";
    }
}
