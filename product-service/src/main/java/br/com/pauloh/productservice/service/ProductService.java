package br.com.pauloh.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pauloh.productservice.model.Product;
import br.com.pauloh.productservice.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(Product product) {
        if (productRepository.findByName(product.getName()).isPresent()) {
            throw new RuntimeException(String.format("Product with name %s already exists", product.getName()));
        }
        productRepository.insert(product);
    }

    public void updateClient(String id, Product product) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Product with id %s not found", id)));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());

        productRepository.save(existingProduct);
    }

    public Product findById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Product with id %s not found", id)));
    }

    public Product findByName(String name) {
        return productRepository.findByName(name).orElseThrow(() -> new RuntimeException(String.format("Product with name %s not found", name)));
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

}
