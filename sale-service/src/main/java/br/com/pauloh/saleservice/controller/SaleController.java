package br.com.pauloh.saleservice.controller;

import java.util.List;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pauloh.saleservice.dto.SaleRequest;
import br.com.pauloh.saleservice.model.Sale;
import br.com.pauloh.saleservice.service.SaleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sale")
@RefreshScope
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeSale(@RequestBody SaleRequest sale) {
        saleService.placeSale(sale);
        return "Sale placed successfully";
    }

    @GetMapping("/all")
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    // @PostMapping
    // public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
    //     saleService.createSale(sale);
    //     return ResponseEntity.status(HttpStatus.CREATED).build();
    // }

}
