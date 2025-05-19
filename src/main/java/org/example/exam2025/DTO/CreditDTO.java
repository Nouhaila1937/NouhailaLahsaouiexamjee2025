package org.example.exam2025.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.exam2025.Enum.StatusCredit;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditDTO {
    private Long id;
    private Date dateDemande;
    private StatusCredit statut;
    private Date dateAcceptation;
    private Double montant;
    private Integer duree;
    private Double tauxInteret;
    private Long clientId;
    private String clientNom;
    private List<Long> remboursementsIds;
    private String creditType; // Pour distinguer le type de crédit
}