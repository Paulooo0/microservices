package br.com.pauloh.saleservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleRequest {
    private List<SaleProductsDto> saleProductsDtoList;
}
