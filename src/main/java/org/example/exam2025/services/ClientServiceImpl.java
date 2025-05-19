package org.example.exam2025.services;



import org.example.exam2025.DTO.ClientDTO;
import org.example.exam2025.Entities.Client;
import org.example.exam2025.Repositories.ClientRepository;
import org.example.exam2025.services.ClientService;
import org.example.exam2025.mappers.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = clientMapper.fromDTO(clientDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toDTO(savedClient);
    }

    @Override
    public ClientDTO getClientById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'ID: " + id));
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        if (clientDTO.getId() == null) {
            throw new RuntimeException("ID du client est requis pour la mise à jour");
        }

        if (!clientRepository.existsById(clientDTO.getId())) {
            throw new RuntimeException("Client non trouvé avec l'ID: " + clientDTO.getId());
        }

        Client client = clientMapper.fromDTO(clientDTO);
        Client updatedClient = clientRepository.save(client);
        return clientMapper.toDTO(updatedClient);
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client non trouvé avec l'ID: " + id);
        }
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDTO findByEmail(String email) {
        Client client = clientRepository.findByEmail(email);
        return clientMapper.toDTO(client);
    }

    @Override
    public List<ClientDTO> getClientsWithCredits() {
        return clientRepository.findAll().stream()
                .filter(client -> client.getCredits() != null && !client.getCredits().isEmpty())
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }
}