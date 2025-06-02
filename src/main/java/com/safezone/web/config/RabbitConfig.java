package com.safezone.web.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue filaAlertas() {
        return new Queue("fila_alertas", false);
    }
}