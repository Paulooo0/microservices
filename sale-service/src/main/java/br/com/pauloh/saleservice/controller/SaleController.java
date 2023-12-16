package br.com.pauloh.saleservice.controller;

import java.util.List;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public Sale placeSale(@RequestBody SaleRequest sale) {
        return saleService.placeSale(sale);
    }

    @GetMapping("/all")
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/id/{saleCode}")
    @ResponseStatus(HttpStatus.OK)
    public Sale findById(@PathVariable String saleCode) {
        return saleService.findById(saleCode);
    }

    @PutMapping("/cancel/{saleCode}")
    @ResponseStatus(HttpStatus.OK)
    public Sale cancelSale(@PathVariable String saleCode) {
        return saleService.cancelSale(saleCode);
    }

    @PutMapping("/finish/{saleCode}")
    @ResponseStatus(HttpStatus.OK)
    public Sale finishSale(@PathVariable String saleCode) {
        return saleService.finishSale(saleCode);
    }
}
