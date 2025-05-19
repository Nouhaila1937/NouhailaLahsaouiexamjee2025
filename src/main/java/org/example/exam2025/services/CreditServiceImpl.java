package org.example.exam2025.services;

import org.example.exam2025.DTO.CreditDTO;
import org.example.exam2025.Entities.Client;
import org.example.exam2025.Entities.Credit;
import org.example.exam2025.Enum.StatusCredit;
import org.example.exam2025.Repositories.ClientRepository;
import org.example.exam2025.Repositories.CreditRepository;
import org.example.exam2025.Repositories.RemboursementRepository;
import org.example.exam2025.services.CreditService;
import org.example.exam2025.mappers.CreditMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final ClientRepository clientRepository;
    private final RemboursementRepository remboursementRepository;
    private final CreditMapper creditMapper;

    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository,
                             ClientRepository clientRepository,
                             RemboursementRepository remboursementRepository,
                             CreditMapper creditMapper) {
        this.creditRepository = creditRepository;
        this.clientRepository = clientRepository;
        this.remboursementRepository = remboursementRepository;
        this.creditMapper = creditMapper;
    }

    @Override
    public CreditDTO saveCreditForClient(CreditDTO creditDTO, Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'ID: " + clientId));

        // Cette méthode reste abstraite car Credit est une classe abstraite
        // Les sous-classes concrètes devront l'implémenter en utilisant leurs propres repositories
        // Voir les implémentations dans les services spécifiques (CreditPersonnelServiceImpl, etc.)
        return null;
    }

    @Override
    public CreditDTO getCreditById(Long id) {
        return creditRepository.findById(id)
                .map(creditMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé avec l'ID: " + id));
    }

    @Override
    public List<CreditDTO> getAllCredits() {
        return creditRepository.findAll().stream()
                .map(creditMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CreditDTO updateCredit(CreditDTO creditDTO) {
        // Cette méthode reste abstraite car Credit est une classe abstraite
        // Les sous-classes concrètes devront l'implémenter
        return null;
    }

    @Override
    public void deleteCredit(Long id) {
        if (!creditRepository.existsById(id)) {
            throw new RuntimeException("Crédit non trouvé avec l'ID: " + id);
        }
        creditRepository.deleteById(id);
    }

    @Override
    public List<CreditDTO> getCreditsByClientId(Long clientId) {
        return creditRepository.findByClientId(clientId).stream()
                .map(creditMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> getCreditsByStatus(StatusCredit status) {
        return creditRepository.findByStatut(status).stream()
                .map(creditMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> getCreditsAfterDate(Date date) {
        return creditRepository.findByDateDemandeAfter(date).stream()
                .map(creditMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> getCreditsByMontantGreaterThan(Double montant) {
        return creditRepository.findByMontantGreaterThan(montant).stream()
                .map(creditMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Double calculerMontantTotal(Long creditId) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé avec l'ID: " + creditId));

        Double montantPrincipal = credit.getMontant();
        Double tauxInteret = credit.getTauxInteret() / 100; // Convertir le pourcentage en decimal
        Integer duree = credit.getDuree(); // Durée en mois

        // Calcul du montant total avec intérêts (formule simplifiée)
        return montantPrincipal * (1 + tauxInteret * duree / 12);
    }

    @Override
    public Boolean estRembourse(Long creditId) {
        Double montantTotal = calculerMontantTotal(creditId);
        Double montantRembourse = remboursementRepository.getSommeRemboursementsByCreditId(creditId);

        // Si montantRembourse est null (pas de remboursements), on retourne false
        if (montantRembourse == null) {
            return false;
        }

        // On considère que le crédit est remboursé si le montant remboursé est égal ou supérieur au montant total
        return montantRembourse >= montantTotal;
    }

    @Override
    public Double montantRestantAPayer(Long creditId) {
        Double montantTotal = calculerMontantTotal(creditId);
        Double montantRembourse = remboursementRepository.getSommeRemboursementsByCreditId(creditId);

        // Si montantRembourse est null (pas de remboursements), le montant restant est égal au montant total
        if (montantRembourse == null) {
            return montantTotal;
        }

        Double montantRestant = montantTotal - montantRembourse;
        // Si le montant restant est négatif (cas de trop-perçu), on retourne 0
        return Math.max(0, montantRestant);
    }
}