package com.safezone.web.controller;

import com.safezone.web.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatAiController {

    private final AiService aiService;

    // Exibe a página de chat
    @GetMapping
    public String exibirChat() {
        return "chat";  // Certifique-se de que 'chat.html' está em templates
    }

    // Processa a pergunta e retorna a resposta
    @PostMapping("/perguntar")
    public String processarPergunta(@RequestParam("mensagem") String mensagem, Model model) {
        String resposta = aiService.perguntar(mensagem);
        model.addAttribute("mensagem", mensagem);
        model.addAttribute("resposta", resposta);
        return "chat";  // Retorna à mesma página com a resposta
    }
}