package com.safezone.web.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "relatorios")
public class Report {

    @Id
    private String id;

    private String category;
    private String location;
    private String description;
}