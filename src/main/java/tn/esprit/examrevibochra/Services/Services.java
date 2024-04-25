package tn.esprit.examrevibochra.Services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.examrevibochra.Entities.*;
import tn.esprit.examrevibochra.Repositorys.IBankRepository;
import tn.esprit.examrevibochra.Repositorys.ICompteRepository;
import tn.esprit.examrevibochra.Repositorys.ITransactionRepository;

import java.time.LocalDate;
import java.util.List;
@EnableScheduling
@Service
@Slf4j
@RequiredArgsConstructor //definit khater sta3mlna final snn naamlo allargs
public class Services implements IServices {
    public final IBankRepository bankRepository;
    public final ICompteRepository compteRepository;
    public final ITransactionRepository transactionRepository;

//@Scheduled(cron= "* */15 * * * *")
//    @Override
//    public void retrive() {
//    log.info("bonjour");
//
//    }

    @Override
    public Bank addBank(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public Compte ajouterCompteEtAffecterAAgence(Compte compte, String agenceBank) {
        Bank banks = bankRepository.findBanksByAgence(agenceBank);
        banks.getCompte().add(compte);
        return compteRepository.save(compte);
    }
    @Transactional
    @Override
    public String ajouterVirement(Transaction transaction) {
        if (transaction.getType()== TypeTransaction.Virment
                &&transaction.getExpliditeur().getTypeCompte()==TypeCompte.Epargne){
            return "On ne peut pas faire un virement à partir d'un compte épargne";
        }

        if (transaction.getType()==TypeTransaction.Virment
                &&transaction.getExpliditeur().getTypeCompte()== TypeCompte.Courant
                &&transaction.getSolde()>transaction.getExpliditeur().getSolde()){
            return "One ne peut pas faire un virement : Solde insuffisant";
        }
        Compte compteExpediteur = compteRepository.findById(transaction.getExpliditeur().getIdCompte()).orElse(null);
        Compte compteDestinataire = compteRepository.findById(transaction.getDestinataire().getIdCompte()).orElse(null);

        compteExpediteur.setSolde(compteExpediteur.getSolde()-transaction.getSolde());
        compteDestinataire.setSolde(compteDestinataire.getSolde()+transaction.getSolde());

        compteRepository.save(compteExpediteur);
        compteRepository.save(compteDestinataire);
        transactionRepository.save(transaction);

        return "Virement de "+ transaction.getSolde() + "DT de compte "
                +compteExpediteur.getCode()+ "vers le compte "
                +compteDestinataire.getCode()+ "approuvé avec succès";
    }


    public String ajouterRetrait(Transaction transaction) {
        if (transaction.getType() != TypeTransaction.Retrait) {
            return "Type de transaction invalide";
        }
   Compte compteExpediteur = compteRepository.findById(transaction.getExpliditeur().getIdCompte()).orElse(null);

//        Compte compte = transaction.getExpliditeur();

        if ( compteExpediteur == null ||  compteExpediteur.getSolde() == null) {
            return "Compte expéditeur invalide";
        }

        double montant = transaction.getSolde();

        if ( compteExpediteur.getSolde() < montant) {
            return "On ne peut pas faire un retrait : Solde insuffisant";
        }

        compteExpediteur.setSolde( compteExpediteur.getSolde() - montant);

        transactionRepository.save(transaction);

        return "RETRAIT de " + montant + " DT de compte " +  compteExpediteur.getCode() + " approuvé avec succès.";
    }
    @Override
    public String ajouterVersement(Transaction transaction) {
        Compte compteDestinataire =
        compteRepository.findById(transaction.getDestinataire().getIdCompte()).orElse(null);
        if (transaction.getType()== TypeTransaction.Versement
                &&compteDestinataire.getTypeCompte()==TypeCompte.Courant){
            transaction.setDateTransaction(LocalDate.now());
            compteDestinataire.setSolde(compteDestinataire.getSolde()+transaction.getSolde()-2);

           compteRepository.save(compteDestinataire);
           transactionRepository.save(transaction);
            return "Versement de"+transaction.getSolde()+" DT de compte "+compteDestinataire.getCode()+" approuvé avec succès.";
        }
        transaction.setDateTransaction(LocalDate.now());
        compteDestinataire.setSolde(compteDestinataire.getSolde()+transaction.getSolde());
        compteRepository.save(compteDestinataire);
        transactionRepository.save(transaction);
        return "Versement de"+transaction.getSolde()+" DT de compte "+compteDestinataire.getCode()+" approuvé avec succès sans frais.";
    }
    @Scheduled(fixedRate = 30000)
    @Override
    public void getAllTransactionByDate() {
        LocalDate today = LocalDate.now();
        List<Transaction> transactions = transactionRepository.findTransactionByDateTransaction(today);
        for (Transaction transaction : transactions) {
            //System.out.println(transaction);
            log.info(transaction.toString());
        }
    }
    @Override
    public List<Transaction> getAllTransactionByBankId(Long idBank) {
        return transactionRepository.getAllTransactionByBankId(idBank);
    }

}

