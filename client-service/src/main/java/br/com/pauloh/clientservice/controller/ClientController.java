package br.com.pauloh.clientservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.pauloh.clientservice.model.Client;
import br.com.pauloh.clientservice.service.ClientService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/client")
@RefreshScope
public class ClientController {

    @Autowired
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client) {
        clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") String id,@RequestBody Client client) {
        clientService.updateClient(id, client);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Client> findById(@PathVariable String id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Client> findByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(clientService.findByCpf(cpf));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Client> deleteById(@PathVariable String id) {
        clientService.deleteById(id);
        return ResponseEntity.ok().build();
    }




    // public ClientController(ClientRepository clientRepository) {
    //     this.clientRepository = clientRepository;
    // }

    // @PostMapping("/client")
    // public ResponseEntity<Client> create(@RequestBody @Valid Client client) {
    //     return ResponseEntity.ok(clientRepository.insert(client));
    // }

    // @PutMapping("/client/{id}")
    // public ResponseEntity<Client> update(@RequestBody @Valid Client client) {
    //     return ResponseEntity.ok(clientRepository.save(client));
    // }

    // @DeleteMapping("/client/{id}")
    // public ResponseEntity<Void> delete(@RequestBody Client client) {
    //     clientRepository.delete(client);
    //     return ResponseEntity.ok().build();
    // }

    // @GetMapping("/client/{cpf}")
    // public ResponseEntity<Client> findById(@RequestBody Client client) {
    //     return ResponseEntity.ok(clientRepository.findByCpf(client.getCpf()).orElse(null));
    // }

    // _____________________________

}
