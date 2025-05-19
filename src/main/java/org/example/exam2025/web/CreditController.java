package org.example.exam2025.web;

import org.example.exam2025.DTO.CreditDTO;
import org.example.exam2025.Enum.StatusCredit;
import org.example.exam2025.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/credits")
@CrossOrigin("*")
public class CreditController {

    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_CLIENT', 'SCOPE_ADMIN', ' SCOPE_EMPLOYEE')")
    public ResponseEntity<CreditDTO> getCreditById(@PathVariable Long id) {
        CreditDTO credit = creditService.getCreditById(id);
        return ResponseEntity.ok(credit);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_CLIENT', 'SCOPE_ADMIN', ' SCOPE_EMPLOYEE')")
    public ResponseEntity<List<CreditDTO>> getAllCredits() {
        List<CreditDTO> credits = creditService.getAllCredits();
        return ResponseEntity.ok(credits);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority( 'SCOPE_ADMIN', ' SCOPE_EMPLOYEE')")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CreditDTO>> getCreditsByClientId(@PathVariable Long clientId) {
        List<CreditDTO> credits = creditService.getCreditsByClientId(clientId);
        return ResponseEntity.ok(credits);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CreditDTO>> getCreditsByStatus(@PathVariable StatusCredit status) {
        List<CreditDTO> credits = creditService.getCreditsByStatus(status);
        return ResponseEntity.ok(credits);
    }

    @GetMapping("/after-date")
    public ResponseEntity<List<CreditDTO>> getCreditsAfterDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<CreditDTO> credits = creditService.getCreditsAfterDate(date);
        return ResponseEntity.ok(credits);
    }

    @GetMapping("/montant-greater-than/{montant}")
    public ResponseEntity<List<CreditDTO>> getCreditsByMontantGreaterThan(@PathVariable Double montant) {
        List<CreditDTO> credits = creditService.getCreditsByMontantGreaterThan(montant);
        return ResponseEntity.ok(credits);
    }

    @GetMapping("/{id}/montant-total")
    public ResponseEntity<Double> calculerMontantTotal(@PathVariable Long id) {
        Double montantTotal = creditService.calculerMontantTotal(id);
        return ResponseEntity.ok(montantTotal);
    }

    @GetMapping("/{id}/est-rembourse")
    public ResponseEntity<Boolean> estRembourse(@PathVariable Long id) {
        Boolean estRembourse = creditService.estRembourse(id);
        return ResponseEntity.ok(estRembourse);
    }

    @GetMapping("/{id}/montant-restant")
    public ResponseEntity<Double> montantRestantAPayer(@PathVariable Long id) {
        Double montantRestant = creditService.montantRestantAPayer(id);
        return ResponseEntity.ok(montantRestant);
    }
}