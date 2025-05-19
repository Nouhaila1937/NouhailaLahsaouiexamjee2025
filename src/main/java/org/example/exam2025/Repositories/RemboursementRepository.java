package org.example.exam2025.Repositories;
import org.example.exam2025.Entities.Remboursement;
import org.example.exam2025.Enum.TypeRemboursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
    List<Remboursement> findByCreditId(Long creditId);
    List<Remboursement> findByType(TypeRemboursement type);
    List<Remboursement> findByDateBetween(Date dateDebut, Date dateFin);

    @Query("SELECT SUM(r.montant) FROM Remboursement r WHERE r.credit.id = :creditId")
    Double getSommeRemboursementsByCreditId(Long creditId);

    @Query("SELECT r FROM Remboursement r WHERE r.credit.client.id = :clientId")
    List<Remboursement> findAllRemboursementsByClientId(Long clientId);
}