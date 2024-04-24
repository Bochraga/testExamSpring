package tn.esprit.examrevibochra.Contollers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examrevibochra.Entities.Transaction;
import tn.esprit.examrevibochra.Services.IServices;

import java.util.List;

@RestController
@RequestMapping("/Client")
@AllArgsConstructor
public class TransactiionRestController {

    private final IServices iService;

    @PostMapping("/virement")
    public String ajouterVirement(@RequestBody Transaction transaction) {
        return iService.ajouterVirement(transaction);
    }
    @PostMapping("/retrait")
    public String ajouterRetrait(@RequestBody Transaction transaction) {
        return iService.ajouterRetrait(transaction);
    }
     @PostMapping("/versment")
    public String ajouterVersement(Transaction transaction){
        return iService.ajouterVersement(transaction);
    }
    @GetMapping("/getTransactionByBank/{idBank}")
    public List<Transaction> getAllTransactionByBank(@PathVariable Long idBank)
    {
        return iService.getAllTransactionByBankId(idBank);
    }

}
