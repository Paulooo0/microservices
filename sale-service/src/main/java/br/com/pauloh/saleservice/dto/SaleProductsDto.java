package br.com.pauloh.saleservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleProductsDto {
    private Long id;

    private String name;

    private BigDecimal price;

    private Integer quantity;
}
