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
public class CadastroController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    // Exibe o formulário de registro
    @GetMapping("/register")
    public String exibirFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register"; // Página HTML do formulário de registro
    }

    // Processa o cadastro de usuário
    @PostMapping("/register")
    public String registrarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario,
                                   BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register"; // Retorna para o formulário se houver erros de validação
        }

        // Verifica se o email já está em uso
        if (usuarioService.buscarPorEmail(usuario.getEmail()).isPresent()) {
            model.addAttribute("erro", "Email já está em uso.");
            return "register";
        }

        // Criptografa a senha antes de salvar
        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha())); // Criptografa a senha
        } else {
            model.addAttribute("erro", "A senha não pode ser vazia.");
            return "register";
        }

        usuarioService.salvar(usuario); // Salva o usuário no banco de dados
        return "redirect:/login?cadastro=sucesso"; // Redireciona para a página de login após o sucesso
    }
}