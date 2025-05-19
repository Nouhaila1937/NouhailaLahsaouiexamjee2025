package org.example.exam2025.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.exam2025.Enum.TypeRemboursement;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Remboursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private Double montant;
    private TypeRemboursement type; // Mensualité, Remboursement anticipé
    @ManyToOne
    private Credit credit;

}