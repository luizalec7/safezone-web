package com.safezone.web.controller;

import com.safezone.web.dto.MensagemDto;
import com.safezone.web.rabbitmq.RabbitMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mensagem")
public class RabbitMqController {

    @Autowired
    private RabbitMqProducer producer;

    @GetMapping
    public String formulario(Model model) {
        model.addAttribute("mensagemDto", new MensagemDto());
        return "mensagem-form";
    }

    @PostMapping
    public String enviarMensagem(@ModelAttribute MensagemDto mensagemDto, Model model) {
        producer.enviarMensagem(mensagemDto.getTexto());
        model.addAttribute("mensagem", "Mensagem enviada com sucesso!");
        model.addAttribute("mensagemDto", new MensagemDto());
        return "mensagem-form";
    }
}