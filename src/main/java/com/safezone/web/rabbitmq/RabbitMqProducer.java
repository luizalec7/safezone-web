package com.safezone.web.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer {

    private final AmqpTemplate amqpTemplate;

    @Value("${fila.nome}")
    private String nomeFila;

    public RabbitMqProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void enviarMensagem(String mensagem) {
        amqpTemplate.convertAndSend(nomeFila, mensagem);
    }
}