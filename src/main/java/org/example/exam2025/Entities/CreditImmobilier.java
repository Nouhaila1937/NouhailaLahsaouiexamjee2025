package org.example.exam2025.Entities;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.exam2025.Enum.Typebien;

@Entity
@DiscriminatorValue("Immobilier")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditImmobilier extends Credit{
    private Typebien typeBien;
}
