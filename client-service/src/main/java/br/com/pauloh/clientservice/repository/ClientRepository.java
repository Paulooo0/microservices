package br.com.pauloh.clientservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.pauloh.clientservice.model.Client;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {
    Optional<Client> findByCpf(String cpf);
}
