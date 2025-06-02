package com.safezone.web.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumer {

    @RabbitListener(queues = "${fila.nome}")
    public void consumirMensagem(String mensagem) {
        System.out.println("Mensagem recebida da fila: " + mensagem);
    }
}