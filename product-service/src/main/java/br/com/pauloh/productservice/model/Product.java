package br.com.pauloh.productservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Document("product")
@Data
@Schema(name = "Product")
public class Product {
    
    @Id
    @Schema(name = "id")
    private String id;

    @NotNull
    @Schema(name = "name", nullable = false)
    private String name;

    @NotNull
    @Schema(name = "price", nullable = false)
    private Double price;

    @NotNull
    @Schema(name = "description", nullable = false)
    private String description;
}
