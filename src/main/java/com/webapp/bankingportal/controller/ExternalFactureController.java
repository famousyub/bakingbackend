package com.webapp.bankingportal.controller;


import com.webapp.bankingportal.dto.AccountResponse;
import com.webapp.bankingportal.entity.Account;
import com.webapp.bankingportal.entity.ExternalFacture;
import com.webapp.bankingportal.repository.AccountRepository;
import com.webapp.bankingportal.repository.ExternalFactureRepository;
import com.webapp.bankingportal.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.webapp.bankingportal.util.LoggedinUser;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/externalfacture")
@RestController
@CrossOrigin("*")
public class ExternalFactureController {


    @Autowired
    private ExternalFactureRepository externalFactureRepository ;


    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private DashboardService dashboardService;

    @PostMapping("/create")
    public ResponseEntity<?> createFacture (@RequestBody ExternalFacture externalFacture)
    {
        String accountNumber = LoggedinUser.getAccountNumber();
        AccountResponse accountResponse = dashboardService.getAccountDetails(accountNumber);
        Account myacoount = accountRepository.findByAccountNumber(accountNumber);
        externalFacture.setAccountnumber(LoggedinUser.getAccountNumber());
        externalFacture.setTransaction_date(new Date());
        Map<String, Object> p = new HashMap<>();
        if(accountResponse.getBalance() >  externalFacture.getAmount()){
            ExternalFacture  exfac= externalFactureRepository.save(externalFacture);
            double newbalance = myacoount.getBalance() -  externalFacture.getAmount();
            myacoount.setBalance(newbalance);
            accountRepository.save(myacoount);
            p.put("facture", exfac);
            p.put("message", "success");
            return  ResponseEntity.ok().body(p);
        }

        else {
            p.put("facture",new ExternalFacture());
            p.put("message","erreur");
         return     ResponseEntity.ok().body(p);
        }

    }

}
