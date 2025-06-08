package com.safezone.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "alertas") // Definindo o nome da coleção no MongoDB
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alerta {

    @Id
    private String id; // O MongoDB irá gerar automaticamente o ID para o alerta

    private String type;
    private String description;
    private String location;

    public Alerta(String type, String description, String location) {
        this.type = type;
        this.description = description;
        this.location = location;
    }
}