package br.com.pauloh.saleservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.pauloh.saleservice.model.Sale;

@Repository
public interface SaleRepository extends MongoRepository<Sale, String> {
    
}
