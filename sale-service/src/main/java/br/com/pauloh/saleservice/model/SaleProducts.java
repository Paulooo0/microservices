package br.com.pauloh.saleservice.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.OnError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_sale_products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Sale Products")
public class SaleProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id")
    private Long id;

    @NotNull
    @Schema(description = "Name")
    private String name;

    @NotNull
    @Schema(description = "Price")
    private BigDecimal price;

    @NotNull
    @Schema(description = "Quantity")
    private Integer quantity;
}
