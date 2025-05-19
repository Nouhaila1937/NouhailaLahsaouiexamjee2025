package org.example.exam2025.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.exam2025.Enum.StatusCredit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "credit_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateDemande;
    private StatusCredit statut;
    private Date dateAcceptation;
    private Double montant;
    private Integer duree;
    private Double tauxInteret;

    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "credit", fetch = FetchType.LAZY)
    private List<Remboursement> remboursements = new ArrayList<>();



}