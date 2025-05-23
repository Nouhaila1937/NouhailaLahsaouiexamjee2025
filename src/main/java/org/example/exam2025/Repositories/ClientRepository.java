package org.example.exam2025.Repositories;

import org.example.exam2025.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);

}
