package br.com.pauloh.saleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pauloh.saleservice.model.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    
}
