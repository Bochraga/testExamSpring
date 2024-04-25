package tn.esprit.examrevibochra.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.examrevibochra.Entities.Compte;
import tn.esprit.examrevibochra.Entities.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
//    @Query("select t from Transaction t JOIN Bank b on t.destinataire member b.compte where b.idBank=:id" )
    @Query("select t from Transaction t JOIN Bank b on ((t.destinataire member b.compte) OR (t.expliditeur member b.compte)) where b.idBank=:id")
    List<Transaction> getAllTransactionByBankId(@Param("id") long idBank);
    List<Transaction> findTransactionByDateTransaction(LocalDate date);
}
