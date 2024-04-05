package com.webapp.bankingportal.controller;


import com.webapp.bankingportal.dto.AccountResponse;
import com.webapp.bankingportal.entity.Account;
import com.webapp.bankingportal.entity.Credit;
import com.webapp.bankingportal.entity.CreditComment;
import com.webapp.bankingportal.repository.AccountRepository;
import com.webapp.bankingportal.repository.CreditRepository;
import com.webapp.bankingportal.service.DashboardService;
import com.webapp.bankingportal.util.LoggedinUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("/api/credit")
@RestController
@CrossOrigin("*")

public class CreditController {

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private DashboardService dashboardService;



/*

String accountNumber = LoggedinUser.getAccountNumber();
        AccountResponse accountResponse = dashboardService.getAccountDetails(accountNumber);
        Account myacoount = accountRepository.findByAccountNumber(accountNumber);
        externalFacture.setAccountnumber(LoggedinUser.getAccountNumber());
 */



    @GetMapping("/credits")
    public ResponseEntity<?> getAllCredits(Pageable pageable) {
        String accountNumber = LoggedinUser.getAccountNumber();

        List<Credit> listscredits  =  creditRepository.getAllCredits(accountNumber);
        return ResponseEntity.ok().body(listscredits);
    }

    @PostMapping("/credits")
    public ResponseEntity<?> createPost(@Valid @RequestBody Credit credit) {
        String accountNumber = LoggedinUser.getAccountNumber();
        AccountResponse accountResponse = dashboardService.getAccountDetails(accountNumber);
        Account myacoount = accountRepository.findByAccountNumber(accountNumber);
        credit.setAccountNumber(accountNumber);
        credit.setUsername(LoggedinUser.getAccountNumber());
        Map<String , Object> p = new HashMap<>();
        if(myacoount.getBalance() > credit.getAmount()){

            p.put("credit",credit);
            p.put("message", "success");
            return ResponseEntity.ok().body(p);
        }
         p.put("credit", new Credit());
         p.put("message", "error insuffisant solde");

         return  ResponseEntity.badRequest().body(p);


    }





}
