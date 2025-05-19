package org.example.exam2025.Repositories;

import org.example.exam2025.Entities.CreditImmobilier;
import org.example.exam2025.Enum.Typebien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditImmobilierRepository extends JpaRepository<CreditImmobilier, Long> {
    List<CreditImmobilier> findByTypeBien(Typebien typeBien);
    List<CreditImmobilier> findByClientId(Long clientId);
    List<CreditImmobilier> findByMontantGreaterThanAndTypeBien(Double montant, Typebien typeBien);
}