package org.example.exam2025.mappers;
import org.example.exam2025.DTO.RemboursementDTO;
import org.example.exam2025.Entities.Remboursement;
import org.springframework.stereotype.Component;

@Component
public class RemboursementMapper {
    public RemboursementDTO toDTO(Remboursement remboursement) {
        if (remboursement == null) return null;
        RemboursementDTO dto = new RemboursementDTO();
        dto.setId(remboursement.getId());
        dto.setDate(remboursement.getDate());
        dto.setMontant(remboursement.getMontant());
        dto.setType(remboursement.getType());
        if (remboursement.getCredit() != null) {
            dto.setCreditId(remboursement.getCredit().getId());
        }
        return dto;
    }

    public Remboursement fromDTO(RemboursementDTO dto) {
        if (dto == null) return null;
        Remboursement remboursement = new Remboursement();
        remboursement.setId(dto.getId());
        remboursement.setDate(dto.getDate());
        remboursement.setMontant(dto.getMontant());
        remboursement.setType(dto.getType());
         return remboursement;
    }
}