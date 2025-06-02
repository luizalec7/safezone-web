package com.safezone.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.OpenAiChatClient;

@Configuration
public class AiConfig {

    @Value("${spring.ai.openai.api-key}")
    private String openAiApiKey;

    @Bean
    public OpenAiApi openAiApi() {
        return new OpenAiApi(openAiApiKey);
    }

    @Bean
    public ChatClient chatClient(OpenAiApi openAiApi) {
        return new OpenAiChatClient(openAiApi);
    }
}