package org.example.exam2025.services;

import org.example.exam2025.DTO.CreditDTO;
import org.example.exam2025.Enum.StatusCredit;

import java.util.Date;
import java.util.List;

public interface CreditService {
    // Opérations CRUD
    CreditDTO saveCreditForClient(CreditDTO creditDTO, Long clientId);
    CreditDTO getCreditById(Long id);
    List<CreditDTO> getAllCredits();
    CreditDTO updateCredit(CreditDTO creditDTO);
    void deleteCredit(Long id);

    // Opérations spécifiques
    List<CreditDTO> getCreditsByClientId(Long clientId);
    List<CreditDTO> getCreditsByStatus(StatusCredit status);
    List<CreditDTO> getCreditsAfterDate(Date date);
    List<CreditDTO> getCreditsByMontantGreaterThan(Double montant);
    Double calculerMontantTotal(Long creditId);
    Boolean estRembourse(Long creditId);
    Double montantRestantAPayer(Long creditId);
}