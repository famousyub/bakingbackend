package com.webapp.bankingportal.service;

import com.webapp.bankingportal.entity.Deposit;
import com.webapp.bankingportal.entity.Send;

import java.math.BigInteger;

public interface SendDepositService {
    public Send save(Send theSend);
    public Deposit saveDeposit(Deposit theDeposit);
    public void setDeposit(BigInteger accountNo, BigInteger sendAccountNo, BigInteger receiveAccountNo, BigInteger amount);
}