package com.webapp.bankingportal.controller;

import com.webapp.bankingportal.dto.AccountResponse;
import com.webapp.bankingportal.entity.*;
import com.webapp.bankingportal.repository.AccountRepository;
import com.webapp.bankingportal.service.*;
import com.webapp.bankingportal.services.BankAccountService;
import com.webapp.bankingportal.util.LoggedinUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class SendDepositController {

    @Autowired
    private SendDepositService sendDepositService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private EmailService mailService;

    @Autowired
    private AccountService bankAccountService;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserService userService;

   /* @PostMapping("/send/{balance}/{id}/{email}")
    public ResponseEntity<?> sendMoney(@PathVariable BigInteger balance, @PathVariable int id, @PathVariable String email,
                                       @RequestBody Send theSend) {
        if (accountRepository.findByAccountNumber(theSend.getReceiveAccountNo()) == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        theSend.setSendingDate(new Date(System.currentTimeMillis()));
        theSend.setStatus(1);
        User theUser = userService.findByEmail(email);
        BankAccount theBankAccount = bankAccountService(theSend.getReceiveAccountNo());
        BigInteger newBalance = balance.subtract(theSend.getAmount());
        BigInteger newAccountNo = theSend.getAccountNo();
        String accountNumber = LoggedinUser.getAccountNumber();
        AccountResponse accountResponse = dashboardService.getAccountDetails(accountNumber);
        Account myacoount = accountRepository.findByAccountNumber(accountNumber);

        Map<String, Object> p = new HashMap<>();
        if(accountResponse.getBalance() >  externalFacture.getAmount()){
            ExternalFacture exfac= externalFactureRepository.save(externalFacture);
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





        int res = theSend.getAmount().compareTo(BigInteger.valueOf(1000));
        if (res < 0) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            sendDepositService.save(theSend);
            sendDepositService.setDeposit(theSend.getReceiveAccountNo(), theSend.getSendAccountNo(), theSend.getReceiveAccountNo(), theSend.getAmount());
            transactionService.setTransactions(theSend.getAccountNo(), theSend.getAmount(), "Send money to " + theBankAccount.getName(),
                    1, "Send Money", "debit");
            transactionService.setTransactions(theSend.getReceiveAccountNo(), theSend.getAmount(),
                    "Receive money from " + theSend.getName(), theSend.getStatus(), "Receive Money", "credit");
            balanceService.updateBalance(newBalance, newAccountNo, id);
            balanceService.updateBalance(addNewBalance, addNewAccountNo, theBalance.getId());
            mailService.transactionMail(email, "Send Money Successfully",
                    "Your send money is successful to." + theBankAccount.getName() + " \n Account No: " + theSend.getAccountNo() + "\nSend Amount: " + theSend.getAmount() +
                            "\nMain Account Balance" + newBalance + "\nDate: " + new Date(System.currentTimeMillis()) + "\n\nThank you for using E Banking appliction.");
            if (theUser != null) {
                mailService.transactionMail(theUser.getEmail(), "Receive Money",
                        "Receive money from." + theSend.getName() + " \n Your account no: " + theSend.getReceiveAccountNo() + "\nReceive Amount: " + theSend.getAmount() +
                                "\nMain Account Balance" + addNewBalance + "\nDate: " + new Date(System.currentTimeMillis()) + "\n\nThank you for using E Banking appliction.");
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    @PostMapping("/send/{balance}/{id}/{email}")
    public ResponseEntity<?> senddeposit(@PathVariable BigInteger balance, @PathVariable int id, @PathVariable String email,
                                         @RequestBody Send theSend){


        return  ResponseEntity.ok().body("ok");
    }



}
