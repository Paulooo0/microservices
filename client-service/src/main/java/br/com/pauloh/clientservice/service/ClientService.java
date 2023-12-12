package br.com.pauloh.clientservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pauloh.clientservice.model.Client;
import br.com.pauloh.clientservice.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void createClient(Client client) {
        if (clientRepository.findByCpf(client.getCpf()).isPresent()) {
            throw new RuntimeException(String.format("Client with cpf %s already exists", client.getCpf()));
        }
        clientRepository.insert(client);
    }

    public void updateClient(String id, Client client) {
        Client savedClient = clientRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(
            String.format("Client with id %s not found", id)));

        savedClient.setFirst_name(client.getFirst_name());
        savedClient.setLast_name(client.getLast_name());
        savedClient.setCpf(client.getCpf());
        savedClient.setBalance(client.getBalance());

        clientRepository.save(savedClient);
    }

    public Client findById(String id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException(
            String.format("Client with cpf %s not found", id)));
    }

    public Client findByCpf(String cpf) {
        return clientRepository.findByCpf(cpf).orElseThrow(() -> new RuntimeException(
            String.format("Client with cpf %s not found", cpf)));
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public void deleteById(String id) {
        clientRepository.deleteById(id);
    }

}
