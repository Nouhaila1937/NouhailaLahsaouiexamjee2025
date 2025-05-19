package org.example.exam2025.mappers;


import org.example.exam2025.DTO.*;
import org.example.exam2025.Entities.*;
import org.springframework.stereotype.Component;

@Component
public class CreditMapper {
    public CreditDTO toDTO(Credit credit) {
        if (credit == null) return null;
        if (credit instanceof CreditPersonnel) {
            CreditPersonnelDTO dto = new CreditPersonnelDTO();
            fillBaseFields(dto, credit);
            dto.setMotif(((CreditPersonnel) credit).getMotif());
            return dto;
        } else if (credit instanceof CreditImmobilier) {
            CreditImmobilierDTO dto = new CreditImmobilierDTO();
            fillBaseFields(dto, credit);
            dto.setTypeBien(((CreditImmobilier) credit).getTypeBien());
            return dto;
        } else if (credit instanceof CreditProfessionnel) {
            CreditProfessionnelDTO dto = new CreditProfessionnelDTO();
            fillBaseFields(dto, credit);
            dto.setMotif(((CreditProfessionnel) credit).getMotif());
            dto.setRaisonSociale(((CreditProfessionnel) credit).getRaisonSociale());
            return dto;
        } else {
            CreditDTO dto = new CreditDTO();
            fillBaseFields(dto, credit);
            return dto;
        }
    }

    private void fillBaseFields(CreditDTO dto, Credit credit) {
        dto.setId(credit.getId());
        dto.setDateDemande(credit.getDateDemande());
        dto.setStatut(credit.getStatut().toString());
        dto.setDateAcception(credit.getDateAcceptation());
        dto.setMontant(credit.getMontant());
        dto.setDureeRemboursement(credit.getDuree());
        dto.setTauxInteret(credit.getTauxInteret());
        if (credit.getClient() != null) dto.setClientId(credit.getClient().getId());
    }
}