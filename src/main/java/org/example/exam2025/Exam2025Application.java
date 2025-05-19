package org.example.exam2025;

import org.example.exam2025.Entities.Client;
import org.example.exam2025.Entities.CreditProfessionnel;
import org.example.exam2025.Entities.Remboursement;
import org.example.exam2025.Enum.TypeRemboursement;
import org.example.exam2025.Enum.Typebien;
import org.example.exam2025.Repositories.ClientRepository;
import org.example.exam2025.Repositories.CreditProfessionnelRepository;
import org.example.exam2025.Repositories.RemboursementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class Exam2025Application {

    public static void main(String[] args) {
        SpringApplication.run(Exam2025Application.class, args);
    }

    @Bean
    CommandLineRunner initData(ClientRepository clientRepository , RemboursementRepository remboursementRepository , CreditProfessionnelRepository creditProfessionnelRepository) {
        return args -> {

            Remboursement remboursement = new Remboursement();
            remboursement.setDate(new Date());
            remboursement.setMontant(1000.0);
            remboursement.setType(TypeRemboursement.REMBOURSEMENT);
            remboursementRepository.save(remboursement);

            // Crée et sauvegarde d'abord le client
            Client client = new Client();
            client.setNom("Nouhaila");
            client.setEmail("nouhaila@gmail.com");
            Client savedClient = clientRepository.save(client);

            CreditProfessionnel cpro = new CreditProfessionnel();
            cpro.setMontant(1000.0);
            cpro.setMotif("motif");
            cpro.setClient(savedClient); // Utilise le client sauvegardé
            cpro.setRaisonSociale("raison sociale");
            cpro.setDuree(12);
            creditProfessionnelRepository.save(cpro);
        };
    }
}
