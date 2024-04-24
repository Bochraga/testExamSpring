package tn.esprit.examrevibochra.Contollers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examrevibochra.Entities.Compte;
import tn.esprit.examrevibochra.Services.IServices;

@RestController
@RequestMapping("/Client")
@RequiredArgsConstructor
public class CompteRestController {
    private  final IServices iService;
    @PostMapping("/afffect/{agenceBank}")
    public Compte ajouterCompteEtAffecterAAgence(@RequestBody Compte compte,@PathVariable String agenceBank) {
        return iService.ajouterCompteEtAffecterAAgence(compte,agenceBank);
    }


}
