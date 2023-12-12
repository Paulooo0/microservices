package br.com.pauloh.saleservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pauloh.saleservice.model.Sale;
import br.com.pauloh.saleservice.repository.SaleRepository;

@Service
public class SaleService {
    
    @Autowired
    private SaleRepository saleRepository;


    public void createSale(Sale sale) {
        // TODO to implement create sale
    }
}
