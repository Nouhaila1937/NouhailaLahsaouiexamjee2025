package org.example.exam2025.Repositories;

import org.example.exam2025.Entities.CreditProfessionnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditProfessionnelRepository extends JpaRepository<CreditProfessionnel, Long> {
    List<CreditProfessionnel> findByMotif(String motif);
    List<CreditProfessionnel> findByRaisonSociale(String raisonSociale);
    List<CreditProfessionnel> findByClientId(Long clientId);
    List<CreditProfessionnel> findByRaisonSocialeContaining(String keyword);
}