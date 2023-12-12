package br.com.pauloh.saleservice.model;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Document("sale")
@Data
@Schema(description = "Sale")
public class Sale {
    
    @Id
    @Schema(description = "Id")
    private String id;

    @NotNull
    @Schema(nullable = false, description = "Cpf")
    private String cpf;

    @NotNull
    @Schema(nullable = false, description = "Products")
    private List<HashMap<String, Integer>> products;

    @NotNull
    @Schema(nullable = false, description = "Total")
    private Double total;

    @NotNull
    @Schema(description = "Created at", accessMode = Schema.AccessMode.READ_ONLY)
    private Instant createdAt = Instant.now();
}
