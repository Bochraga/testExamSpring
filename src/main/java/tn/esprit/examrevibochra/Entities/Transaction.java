package tn.esprit.examrevibochra.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idTranscation;
    TypeTransaction type;
    Long code;
    Double solde;
    LocalDate dateTransaction;
    @ManyToOne
    Compte expliditeur;
    @ManyToOne
    Compte destinataire;
}
