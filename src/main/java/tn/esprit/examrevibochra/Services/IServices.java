package tn.esprit.examrevibochra.Services;


import tn.esprit.examrevibochra.Entities.Bank;
import tn.esprit.examrevibochra.Entities.Compte;
import tn.esprit.examrevibochra.Entities.Transaction;

import java.util.List;

public interface IServices {
//    void retrive();
    Bank addBank(Bank bank);

    Compte ajouterCompteEtAffecterAAgence(Compte compte, String agenceBank);
     String ajouterVirement(Transaction transaction);
    String ajouterRetrait(Transaction transaction);
    String ajouterVersement(Transaction transaction);
    void getAllTransactionByDate();
    List<Transaction> getAllTransactionByBankId(Long idBank);
}


