package org.example.exam2025.services;

import org.example.exam2025.DTO.RemboursementDTO;
import org.example.exam2025.Enum.TypeRemboursement;

import java.util.Date;
import java.util.List;

public interface RemboursementService {
    // Opérations CRUD
    RemboursementDTO saveRemboursement(RemboursementDTO remboursementDTO, Long creditId);
    RemboursementDTO getRemboursementById(Long id);
    List<RemboursementDTO> getAllRemboursements();
    RemboursementDTO updateRemboursement(RemboursementDTO remboursementDTO);
    void deleteRemboursement(Long id);

    // Opérations spécifiques
    List<RemboursementDTO> getRemboursementsByCreditId(Long creditId);
    List<RemboursementDTO> getRemboursementsByType(TypeRemboursement type);
    List<RemboursementDTO> getRemboursementsBetweenDates(Date dateDebut, Date dateFin);
    Double getSommeRemboursementsByCreditId(Long creditId);
    List<RemboursementDTO> getAllRemboursementsByClientId(Long clientId);
}