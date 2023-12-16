package br.com.pauloh.saleservice.service;

import java.util.List;
import java.util.UUID;

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

    public Sale placeSale(SaleRequest saleRequest) {
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

        sale.setSaleCode(UUID.randomUUID().toString());
        sale.setClientCpf(saleRequest.getClientCpf());
        sale.setSaleProductsList(saleProducts);
        sale.setStatus(SaleStatus.OPEN);
        
        return saleRepository.save(sale);
    }

    private SaleProducts mapToDto(SaleProductsDto saleProductsDto) {
        SaleProducts saleProducts = new SaleProducts();

        String getProduct = webClient.get()
            .uri("http://localhost:8082/product/name/" + saleProductsDto.getName())
            .retrieve()
            .bodyToMono(String.class)
            .block();

        if (getProduct.isEmpty()) {
            throw new RuntimeException("Product does not exist");
        }

        saleProducts.setName(saleProductsDto.getName());
        saleProducts.setPrice(saleProductsDto.getPrice());
        saleProducts.setQuantity(saleProductsDto.getQuantity());
        return saleProducts;
    }

    public Sale findById(String saleCode) {
        return saleRepository.findBySaleCode(saleCode).orElseThrow(() -> new RuntimeException(
            String.format("Sale with code %s not found", saleCode)));
    }

    public Sale finishSale(String saleCode) {
        Sale savedSale = saleRepository.findBySaleCode(saleCode).orElseThrow(() -> new RuntimeException(
        String.format("Sale with code %s not found", saleCode)));

        savedSale.setStatus(SaleStatus.FINISHED);
        return saleRepository.save(savedSale);
    }

    public Sale cancelSale(String saleCode) {
        Sale savedSale = saleRepository.findBySaleCode(saleCode).orElseThrow(() -> new RuntimeException(
        String.format("Sale with code %s not found", saleCode)));

        savedSale.setStatus(SaleStatus.CANCELED);
        return saleRepository.save(savedSale);
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
}
