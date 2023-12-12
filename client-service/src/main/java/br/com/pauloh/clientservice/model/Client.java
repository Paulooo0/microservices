package br.com.pauloh.clientservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Document(collection = "client")
@Data
@Schema(name = "Client")
public class Client {

    @Id
    @Schema(description = "Client id")
    private String id;

    @Field("first_name")
    @NotNull
    @Size(min = 1, max = 32)
    @Schema(description = "Client first name")
    private String first_name;

    @Field("last_name")
    @NotNull
    @Size(min = 1, max = 32)
    @Schema(description = "Client last name")
    private String last_name;

    @Field("cpf")
    @NotNull
    @Size(min = 11, max = 11)
    @Indexed(unique = true, background = true)
    @Schema(description = "Client cpf", nullable = false)
    private String cpf;

    @Field("balance")
    @NotNull
    @Schema(description = "Client balance")
    private Double balance;
}
