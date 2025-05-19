package org.example.exam2025.mappers;


import org.example.exam2025.DTO.ClientDTO;
import org.example.exam2025.Entities.Client;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    /**
     * Convertit une entité Client en DTO
     */
    public ClientDTO toDTO(Client client) {
        if (client == null) {
            return null;
        }

        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setEmail(client.getEmail());

        if (client.getCredits() != null) {
            dto.setCreditsIds(client.getCredits().stream()
                    .map(credit -> credit.getId())
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    /**
     * Convertit un DTO Client en entité
     */
    public Client toEntity(ClientDTO dto) {
        if (dto == null) {
            return null;
        }

        Client client = new Client();
        client.setId(dto.getId());
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());

        // Les relations sont généralement gérées séparément

        return client;
    }

    /**
     * Convertit une liste d'entités en liste de DTOs
     */
    public List<ClientDTO> toDTOList(List<Client> clients) {
        if (clients == null) {
            return null;
        }

        return clients.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}