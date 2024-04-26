package com.webapp.bankingportal.controller;

import com.webapp.bankingportal.dto.*;
import com.webapp.bankingportal.entity.User;
import com.webapp.bankingportal.repository.UserRepository;
import com.webapp.bankingportal.service.AccountService;
import com.webapp.bankingportal.service.TransactionService;
import com.webapp.bankingportal.util.LoggedinUser;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin("*")

@RequestMapping("/api/myaccounts")
public class MyAccountSelectController {



    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;



    @Autowired
    private UserRepository userRepository ;
    @GetMapping("/pin/check/{accountnumber}")
    public ResponseEntity<?> checkAccountPIN(@PathVariable("accountnumber")  String accountNumber) {

        User user  = userRepository.findByAccountAccountNumber(accountNumber);

        boolean isPINValid = accountService.isPinCreated(accountNumber);

        Map<String, Object> result = new HashMap<>();
        result.put("hasPIN", isPINValid);

        if (isPINValid) {
            result.put("msg", "PIN Created");

        } else {
            result.put("msg", "Pin Not Created");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/pin/create/{accountNumber}")
    public ResponseEntity<?> createPIN(@RequestBody PinRequest pinRequest, @PathVariable("accountNumber") String accountNumber) {
        accountService.createPIN(accountNumber, pinRequest.getPassword(), pinRequest.getPin());

        Map<String, String> response = new HashMap<>();
        response.put("msg", "PIN created successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/pin/update/{accountNumber}")
    public ResponseEntity<?> updatePIN(@RequestBody PinUpdateRequest pinUpdateRequest,@PathVariable("accountNumber") String accountNumber) {
        accountService.updatePIN(accountNumber, pinUpdateRequest.getOldPin(),
                pinUpdateRequest.getPassword(), pinUpdateRequest.getNewPin());

        Map<String, String> response = new HashMap<>();
        response.put("msg", "PIN updated successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/deposit/{accountNumber}")
    public ResponseEntity<?> cashDeposit(@RequestBody AmountRequest amountRequest,@PathVariable("accountNumber") String accountNumber) {

        if (amountRequest.getAmount() <= 0) {
            Map<String, String> err = new HashMap<>();
            err.put("Error", "Invalid amount");
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }

        accountService.cashDeposit(accountNumber, amountRequest.getPin(), amountRequest.getAmount());

        Map<String, String> response = new HashMap<>();
        response.put("msg", "Cash deposited successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/withdraw/{accountNumber}")
    public ResponseEntity<?> cashWithdrawal(@RequestBody AmountRequest amountRequest,@PathVariable("accountNumber") String accountNumber) {
        if (amountRequest.getAmount() <= 0) {
            Map<String, String> err = new HashMap<>();
            err.put("Error", "Invalid amount");
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        accountService.cashWithdrawal(accountNumber, amountRequest.getPin(),
                amountRequest.getAmount());

        Map<String, String> response = new HashMap<>();
        response.put("msg", "Cash withdrawn successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/fund-transfer/{accountNumber}")
    public ResponseEntity<?> fundTransfer(@RequestBody FundTransferRequest fundTransferRequest,@PathVariable("accountNumber") String accountNumber) {
        if (fundTransferRequest.getAmount() <= 0) {
            Map<String, String> err = new HashMap<>();
            err.put("Error", "Invalid amount");
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }

        accountService.fundTransfer(accountNumber, fundTransferRequest.getTargetAccountNumber(),
                fundTransferRequest.getPin(), fundTransferRequest.getAmount());
        Map<String, String> response = new HashMap<>();
        response.put("msg", "Fund transferred successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/transactions/{accountNumber}")
    public ResponseEntity<List<TransactionDTO>> getAllTransactionsByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
        List<TransactionDTO> transactions = transactionService
                .getAllTransactionsByAccountNumber(accountNumber);
        return ResponseEntity.ok(transactions);
    }
}
