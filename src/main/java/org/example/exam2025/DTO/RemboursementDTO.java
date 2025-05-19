package org.example.exam2025.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.exam2025.Enum.TypeRemboursement;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemboursementDTO {
    private Long id;
    private Date date;
    private Double montant;
    private TypeRemboursement type;
    private Long creditId;
  }