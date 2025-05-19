package org.example.exam2025.services;

import org.example.exam2025.DTO.RemboursementDTO;
import org.example.exam2025.Entities.Credit;
import org.example.exam2025.Entities.Remboursement;
import org.example.exam2025.Enum.TypeRemboursement;
import org.example.exam2025.Repositories.CreditRepository;
import org.example.exam2025.Repositories.RemboursementRepository;
import org.example.exam2025.mappers.RemboursementMapper;
import org.example.exam2025.services.RemboursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RemboursementServiceImpl implements RemboursementService {

    private final RemboursementRepository remboursementRepository;
    private final CreditRepository creditRepository;
    private final RemboursementMapper remboursementMapper;

    @Autowired
    public RemboursementServiceImpl(RemboursementRepository remboursementRepository,
                                    CreditRepository creditRepository,
                                    RemboursementMapper remboursementMapper) {
        this.remboursementRepository = remboursementRepository;
        this.creditRepository = creditRepository;
        this.remboursementMapper = remboursementMapper;
    }

    @Override
    public RemboursementDTO saveRemboursement(RemboursementDTO remboursementDTO, Long creditId) {
        Remboursement remboursement = remboursementMapper.fromDTO(remboursementDTO);
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Credit not found with ID: " + creditId));

        remboursement.setCredit(credit);
        Remboursement savedRemboursement = remboursementRepository.save(remboursement);
        return remboursementMapper.toDTO(savedRemboursement);
    }

    @Override
    public RemboursementDTO getRemboursementById(Long id) {
        Remboursement remboursement = remboursementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Remboursement not found with ID: " + id));
        return remboursementMapper.toDTO(remboursement);
    }

    @Override
    public List<RemboursementDTO> getAllRemboursements() {
        List<Remboursement> remboursements = remboursementRepository.findAll();
        return remboursements.stream()
                .map(remboursementMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RemboursementDTO updateRemboursement(RemboursementDTO remboursementDTO) {
        // Vérifier que le remboursement existe
        remboursementRepository.findById(remboursementDTO.getId())
                .orElseThrow(() -> new RuntimeException("Remboursement not found with ID: " + remboursementDTO.getId()));

        Remboursement remboursement = remboursementMapper.fromDTO(remboursementDTO);

        // Si le creditId est défini, récupérer et associer le crédit
        if (remboursementDTO.getCreditId() != null) {
            Credit credit = creditRepository.findById(remboursementDTO.getCreditId())
                    .orElseThrow(() -> new RuntimeException("Credit not found with ID: " + remboursementDTO.getCreditId()));
            remboursement.setCredit(credit);
        }

        Remboursement updatedRemboursement = remboursementRepository.save(remboursement);
        return remboursementMapper.toDTO(updatedRemboursement);
    }

    @Override
    public void deleteRemboursement(Long id) {
        if (!remboursementRepository.existsById(id)) {
            throw new RuntimeException("Remboursement not found with ID: " + id);
        }
        remboursementRepository.deleteById(id);
    }

    @Override
    public List<RemboursementDTO> getRemboursementsByCreditId(Long creditId) {
        List<Remboursement> remboursements = remboursementRepository.findByCreditId(creditId);
        return remboursements.stream()
                .map(remboursementMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RemboursementDTO> getRemboursementsByType(TypeRemboursement type) {
        List<Remboursement> remboursements = remboursementRepository.findByType(type);
        return remboursements.stream()
                .map(remboursementMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RemboursementDTO> getRemboursementsBetweenDates(Date dateDebut, Date dateFin) {
        List<Remboursement> remboursements = remboursementRepository.findByDateBetween(dateDebut, dateFin);
        return remboursements.stream()
                .map(remboursementMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Double getSommeRemboursementsByCreditId(Long creditId) {
        return remboursementRepository.getSommeRemboursementsByCreditId(creditId);
    }

    @Override
    public List<RemboursementDTO> getAllRemboursementsByClientId(Long clientId) {
        List<Remboursement> remboursements = remboursementRepository.findAllRemboursementsByClientId(clientId);
        return remboursements.stream()
                .map(remboursementMapper::toDTO)
                .collect(Collectors.toList());
    }
}