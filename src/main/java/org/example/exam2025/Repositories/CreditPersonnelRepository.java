package org.example.exam2025.Repositories;


import org.example.exam2025.Entities.CreditPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditPersonnelRepository extends JpaRepository<CreditPersonnel, Long> {
    List<CreditPersonnel> findByMotif(String motif);
    List<CreditPersonnel> findByClientId(Long clientId);
    List<CreditPersonnel> findByMotifContaining(String motifKeyword);
}