package tn.esprit.examrevibochra.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.examrevibochra.Entities.Compte;
import tn.esprit.examrevibochra.Entities.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("")
    List<Transaction> getAllTransactionByBankId(long idBank);
    List<Transaction> findTransactionByDateTransaction(LocalDate date);
}
