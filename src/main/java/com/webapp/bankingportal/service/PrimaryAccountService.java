package com.webapp.bankingportal.service;


import com.webapp.bankingportal.entity.PrimaryAccount;
import com.webapp.bankingportal.entity.SavingsAccount;

import java.security.Principal;

public interface PrimaryAccountService {
    PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();
    public void deposit(String accountType, double amount, Principal principal);

    void withdraw(String accountType, double amount, Principal principal) throws Exception;
}