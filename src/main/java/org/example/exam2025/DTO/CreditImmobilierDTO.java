package org.example.exam2025.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.exam2025.Enum.Typebien;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditImmobilierDTO extends CreditDTO {
    private Typebien typeBien;
}