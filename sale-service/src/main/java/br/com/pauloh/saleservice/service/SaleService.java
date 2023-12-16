package br.com.pauloh.saleservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.pauloh.saleservice.model.SaleProducts;
import br.com.pauloh.saleservice.dto.SaleProductsDto;
import br.com.pauloh.saleservice.dto.SaleRequest;
import br.com.pauloh.saleservice.model.Sale;
import br.com.pauloh.saleservice.repository.SaleRepository;
import br.com.pauloh.saleservice.view.SaleStatus;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final WebClient webClient;

    public void placeSale(SaleRequest saleRequest) {
        Sale sale = new Sale();

        String getClient = webClient.get()
            .uri("http://localhost:8081/client/cpf/" + saleRequest.getClientCpf())
            .retrieve()
            .bodyToMono(String.class)
            .block();

        if (getClient.isEmpty()) {
            throw new RuntimeException("Client does not exist");
        }
        
        List<SaleProducts> saleProducts = saleRequest.getSaleProductsDtoList()
            .stream()
            .map(this::mapToDto)
            .toList();

        sale.setClientCpf(saleRequest.getClientCpf());
        sale.setSaleProductsList(saleProducts);
        sale.setStatus(SaleStatus.OPEN);
        
        saleRepository.save(sale);
    }

    private SaleProducts mapToDto(SaleProductsDto saleProductsDto) {
        SaleProducts saleProducts = new SaleProducts();

        String getProduct = webClient.get()
            .uri("http://localhost:8082/product/name/" + saleProductsDto.getName())
            .retrieve()
            .bodyToMono(String.class)
            .block();

        if (getProduct.isEmpty()) {
            throw new RuntimeException("Client does not exist");
        }

        saleProducts.setName(saleProductsDto.getName());
        saleProducts.setPrice(saleProductsDto.getPrice());
        saleProducts.setQuantity(saleProductsDto.getQuantity());
        return saleProducts;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
}
