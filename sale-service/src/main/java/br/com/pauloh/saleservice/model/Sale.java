package br.com.pauloh.saleservice.model;

import java.time.Instant;
import java.util.List;

import br.com.pauloh.saleservice.view.SaleStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_sale")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Sale")
public class Sale {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id")
    private Long id;

    @NotNull
    @Schema(nullable = false, description = "Sale code")
    private String saleCode;

    @NotNull
    @Schema(nullable = false, description = "Client CPF")
    private String clientCpf;

    @NotNull
    @Schema(nullable = false, description = "Products")
    @OneToMany(cascade = CascadeType.ALL)
    private List<SaleProducts> saleProductsList;

    @NotNull
    @Schema(description = "Status")
    private SaleStatus status;

    @NotNull
    @Schema(description = "Created at")
    private Instant createdAt = Instant.now();
}
