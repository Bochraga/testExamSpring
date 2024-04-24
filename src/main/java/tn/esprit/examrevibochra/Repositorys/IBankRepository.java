package tn.esprit.examrevibochra.Repositorys;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.examrevibochra.Entities.Bank;
import tn.esprit.examrevibochra.Entities.Compte;

import java.util.List;

public interface IBankRepository extends CrudRepository<Bank,Long> {
    Bank findBanksByAgence( String agence);
}
