package com.safezone.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.ChatMessage;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AiService {

    private final ChatClient chatClient;

    // Método para fazer perguntas e obter respostas usando o ChatClient da OpenAI
    public String perguntar(String pergunta) {
        ChatMessage mensagem = new ChatMessage(MessageType.USER, pergunta);
        Prompt prompt = new Prompt(List.of(mensagem));
        return chatClient.call(prompt).getResult().getOutput().getContent();
    }
}