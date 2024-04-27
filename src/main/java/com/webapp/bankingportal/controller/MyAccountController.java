package com.webapp.bankingportal.controller;

import com.webapp.bankingportal.dto.AccountResponse;
import com.webapp.bankingportal.dto.UserResponse;
import com.webapp.bankingportal.entity.Account;
import com.webapp.bankingportal.entity.BankAccType;
import com.webapp.bankingportal.entity.ExternalFacture;
import com.webapp.bankingportal.entity.User;
import com.webapp.bankingportal.repository.AccountRepository;
import com.webapp.bankingportal.repository.BankAccTypeRepository;
import com.webapp.bankingportal.repository.BankAccountRepository;
import com.webapp.bankingportal.repository.UserRepository;
import com.webapp.bankingportal.service.AccountService;
import com.webapp.bankingportal.service.DashboardService;
import com.webapp.bankingportal.service.TransactionService;
import com.webapp.bankingportal.util.LoggedinUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/myaccounts")
public class MyAccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;


    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private BankAccountRepository bankAccountRepository;

     @Autowired
     private BankAccTypeRepository bankAccTypeRepository;

     @Autowired
    UserRepository userRepository;

    @PostMapping("/create/{id}")
    public ResponseEntity<?> createAccounts(@RequestBody Account account, @PathVariable("id") Long account_type)
    {

        try {
            String accountNumber = LoggedinUser.getAccountNumber();

            UserResponse userResponse = dashboardService.getUserDetails(accountNumber);
            Account myacoount = new Account();
            User u = userRepository.findByEmail(userResponse.getEmail());

            String accountNumbergen = generateUniqueAccountNumber();
            myacoount.setPin(account.getPin());
            myacoount.setAccount_type(account.getAccount_type());
            myacoount.setBranch("INTERNAL");
            myacoount.setBalance(account.getBalance());
            myacoount.setUser(u);
            myacoount.setAccountNumber(accountNumbergen);
            myacoount.setIFSC_code("BNA-ACCOUNT");
            myacoount.setRemoved(false);
            BankAccType bankAccType = bankAccTypeRepository.getReferenceById(account_type);
            myacoount.setBankAccType(bankAccType);


            Map<String, Object> p = new HashMap<>();


            accountRepository.save(myacoount);
            p.put("facture", myacoount);
            p.put("message", "success");
            return ResponseEntity.ok().body(p);

        }catch (Exception ex)
        {
            Map<String, Object> p = new HashMap<>();
            p.put("facture", new Account());
            p.put("message", "error" +ex.toString()) ;
            return ResponseEntity.ok().body(p);
        }



    }

    @GetMapping("/myaccount")
 public  ResponseEntity<?> myaccounts()
    {
        List<Account> acoouts  = accountRepository.findAll();

        List<Account> l =   acoouts.stream().filter(el->{

        return     el.getAccountNumber() .equals( LoggedinUser.getAccountNumber());

        }).toList();



        return  ResponseEntity.ok().body(l);

    }

    private String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            // Generate a UUID as the account number
            accountNumber = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
        } while (accountRepository.findByAccountNumber(accountNumber) != null);

        return accountNumber;
    }

}
