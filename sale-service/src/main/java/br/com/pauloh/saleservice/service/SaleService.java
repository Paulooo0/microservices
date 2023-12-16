package br.com.pauloh.saleservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.pauloh.saleservice.model.SaleProducts;
import br.com.pauloh.saleservice.dto.SaleProductsDto;
import br.com.pauloh.saleservice.dto.SaleRequest;
import br.com.pauloh.saleservice.model.Sale;
import br.com.pauloh.saleservice.repository.SaleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final WebClient webClient;

    public void placeSale(SaleRequest saleRequest) {
        Sale sale = new Sale();
        
        List<SaleProducts> saleProducts = saleRequest.getSaleProductsDtoList()
        .stream()
        .map(this::mapToDto)
        .toList();

        sale.setSaleProductsList(saleProducts);

        saleRepository.save(sale);
    }

    // public void createSale(Sale sale) {

    //     String client = webClient.get()
    //     .uri("http://localhost:8081/client/cpf/" + sale.getClientCpf())
    //     .retrieve()
    //     .bodyToMono(String.class)
    //     .block();

    //     if (client == null) {
    //         throw new RuntimeException("Client does not exist");
    //     }

    //     List<SaleProducts> productsList = new ArrayList<>();

    //     for (SaleProducts product : sale.getSaleProductsList()) {
    //         ResponseEntity<SaleProducts> productResponse  = webClient.get()
    //         .uri("http://localhost:8082/product/name/" + product.getName())
    //         .retrieve()
    //         .toEntity(SaleProducts.class)
    //         .block();

    //         if (productResponse  == null || productResponse.getBody() == null) {
    //             throw new RuntimeException("Products does not exist");
    //         }

    //         SaleProducts retrievedProduct = productResponse.getBody();
    //         retrievedProduct.setQuantity(product.getQuantity());
    //         productsList.add(retrievedProduct);
            
    //     }

    //     sale.setClientCpf(client);
    //     sale.setSaleProductsList(productsList);

    //     saleRepository.insert(sale);
    // }

    private SaleProducts mapToDto(SaleProductsDto saleProductsDto) {
        SaleProducts saleProducts = new SaleProducts();
        saleProducts.setName(saleProductsDto.getName());
        saleProducts.setPrice(saleProductsDto.getPrice());
        saleProducts.setQuantity(saleProductsDto.getQuantity());
        return saleProducts;
    }
}
