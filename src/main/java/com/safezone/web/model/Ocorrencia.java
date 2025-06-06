package com.safezone.web.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "ocorrencia")
public class Ocorrencia {

    @Id
    private String id;

    @NotBlank(message = "Categoria é obrigatória")
    private String categoria;

    @NotBlank(message = "Localização é obrigatória")
    private String localizacao;

    private String descricao;

    @NotNull
    private LocalDateTime dataHora = LocalDateTime.now();

    @DBRef
    private Usuario usuario;
}