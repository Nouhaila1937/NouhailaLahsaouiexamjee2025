package org.example.exam2025.services;


import org.example.exam2025.DTO.ClientDTO;

import java.util.List;

public interface ClientService {
    // Opérations CRUD
    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO getClientById(Long id);
    List<ClientDTO> getAllClients();
    ClientDTO updateClient(ClientDTO clientDTO);
    void deleteClient(Long id);

    // Opérations spécifiques
    ClientDTO findByEmail(String email);
    List<ClientDTO> getClientsWithCredits();
}