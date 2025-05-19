package org.example.exam2025.Repositories;

import org.example.exam2025.Entities.Credit;
import org.example.exam2025.Enum.StatusCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByClientId(Long clientId);
    List<Credit> findByStatut(StatusCredit statut);
    List<Credit> findByDateDemandeAfter(Date date);
    List<Credit> findByMontantGreaterThan(Double montant);
}