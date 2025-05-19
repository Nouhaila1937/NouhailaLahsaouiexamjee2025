package org.example.exam2025.web;


import org.example.exam2025.DTO.RemboursementDTO;
import org.example.exam2025.Enum.TypeRemboursement;
import org.example.exam2025.services.RemboursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class RemboursementController {

    private final RemboursementService remboursementService;

    @Autowired
    public RemboursementController(RemboursementService remboursementService) {
        this.remboursementService = remboursementService;
    }

    @PostMapping("/credit/{creditId}")
    public ResponseEntity<RemboursementDTO> createRemboursement(
            @RequestBody RemboursementDTO remboursementDTO,
            @PathVariable Long creditId) {
        RemboursementDTO savedRemboursement = remboursementService.saveRemboursement(remboursementDTO, creditId);
        return new ResponseEntity<>(savedRemboursement, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemboursementDTO> getRemboursementById(@PathVariable Long id) {
        RemboursementDTO remboursement = remboursementService.getRemboursementById(id);
        return ResponseEntity.ok(remboursement);
    }

    @GetMapping
    public ResponseEntity<List<RemboursementDTO>> getAllRemboursements() {
        List<RemboursementDTO> remboursements = remboursementService.getAllRemboursements();
        return ResponseEntity.ok(remboursements);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemboursementDTO> updateRemboursement(
            @PathVariable Long id,
            @RequestBody RemboursementDTO remboursementDTO) {
        remboursementDTO.setId(id);
        RemboursementDTO updatedRemboursement = remboursementService.updateRemboursement(remboursementDTO);
        return ResponseEntity.ok(updatedRemboursement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRemboursement(@PathVariable Long id) {
        remboursementService.deleteRemboursement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/credit/{creditId}")
    public ResponseEntity<List<RemboursementDTO>> getRemboursementsByCreditId(@PathVariable Long creditId) {
        List<RemboursementDTO> remboursements = remboursementService.getRemboursementsByCreditId(creditId);
        return ResponseEntity.ok(remboursements);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<RemboursementDTO>> getRemboursementsByType(@PathVariable TypeRemboursement type) {
        List<RemboursementDTO> remboursements = remboursementService.getRemboursementsByType(type);
        return ResponseEntity.ok(remboursements);
    }

    @GetMapping("/between-dates")
    public ResponseEntity<List<RemboursementDTO>> getRemboursementsBetweenDates(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDebut,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFin) {
        List<RemboursementDTO> remboursements = remboursementService.getRemboursementsBetweenDates(dateDebut, dateFin);
        return ResponseEntity.ok(remboursements);
    }

    @GetMapping("/credit/{creditId}/somme")
    public ResponseEntity<Double> getSommeRemboursementsByCreditId(@PathVariable Long creditId) {
        Double somme = remboursementService.getSommeRemboursementsByCreditId(creditId);
        return ResponseEntity.ok(somme);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<RemboursementDTO>> getAllRemboursementsByClientId(@PathVariable Long clientId) {
        List<RemboursementDTO> remboursements = remboursementService.getAllRemboursementsByClientId(clientId);
        return ResponseEntity.ok(remboursements);
    }
}