package br.com.pauloh.productservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.pauloh.productservice.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByName(String name);
}
