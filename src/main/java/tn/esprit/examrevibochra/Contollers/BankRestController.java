package tn.esprit.examrevibochra.Contollers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.examrevibochra.Entities.Bank;
import tn.esprit.examrevibochra.Services.IServices;

@RestController
@RequestMapping("/Client")
@RequiredArgsConstructor
public class BankRestController {
    private  final IServices iService;
    @PostMapping("/add")
    public Bank addBank (@RequestBody Bank bank){
        return iService.addBank(bank);
    }



}
