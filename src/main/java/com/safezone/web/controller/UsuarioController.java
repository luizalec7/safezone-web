package com.safezone.web.controller;

import com.safezone.web.model.Usuario;
import com.safezone.web.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/perfil")
    public String perfil(Model model, HttpSession session) {
        String usuarioId = (String) session.getAttribute("usuarioId");

        if (usuarioId == null) {
            return "redirect:/login";
        }

        Usuario usuario = usuarioService.buscarPorId(usuarioId).orElseThrow();
        model.addAttribute("usuario", usuario);
        return "profile";
    }

    @PostMapping("/atualizar")
    public String atualizar(@ModelAttribute("usuario") Usuario usuarioForm, HttpSession session) {
        String usuarioId = (String) session.getAttribute("usuarioId");
        if (usuarioId == null) return "redirect:/login";

        Optional<Usuario> usuarioOpt = usuarioService.buscarPorId(usuarioId);
        if (usuarioOpt.isEmpty()) return "redirect:/login";

        Usuario usuario = usuarioOpt.get();
        usuario.setEmail(usuarioForm.getEmail());
        usuario.setSenha(usuarioForm.getSenha()); // 🔒 Recomendado: aplicar criptografia aqui

        usuarioService.atualizarUsuario(usuario);
        return "redirect:/usuario/perfil";
    }

    @PostMapping("/upload-foto")
    public String uploadFoto(@RequestParam("foto") MultipartFile foto, HttpSession session) {
        String usuarioId = (String) session.getAttribute("usuarioId");
        if (usuarioId == null || foto.isEmpty()) return "redirect:/login";

        String pastaDestino = "uploads/";
        String nomeArquivo = System.currentTimeMillis() + "_" + foto.getOriginalFilename();

        try {
            File diretorio = new File(pastaDestino);
            if (!diretorio.exists()) diretorio.mkdirs(); // Garante que a pasta exista

            File destino = new File(pastaDestino + nomeArquivo);
            foto.transferTo(destino);

            String caminhoWeb = "/uploads/" + nomeArquivo;
            usuarioService.atualizarFotoPerfil(usuarioId, caminhoWeb);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/usuario/perfil";
    }
}