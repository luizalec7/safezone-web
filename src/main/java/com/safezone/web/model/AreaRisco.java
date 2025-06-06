package com.safezone.web.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "area_risco")
public class AreaRisco {

    @Id
    private String id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    private String estado;

    @NotBlank(message = "Nível de risco é obrigatório")
    private String nivelRisco;

    private String coordenadas;
}