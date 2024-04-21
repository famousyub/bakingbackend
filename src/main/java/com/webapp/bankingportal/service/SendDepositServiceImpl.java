package com.webapp.bankingportal.service;

import com.webapp.bankingportal.entity.Deposit;
import com.webapp.bankingportal.entity.Send;
import com.webapp.bankingportal.repository.DepositRepository;
import com.webapp.bankingportal.repository.SendRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;

@Service
@Transactional
public class SendDepositServiceImpl implements SendDepositService{

    @Autowired
    private SendRepository sendRepository;
    @Autowired
    private DepositRepository depositRepository;

    @Override
    public Send save(Send theSend) {
        return sendRepository.save(theSend);
    }

    @Override
    public Deposit saveDeposit(Deposit theDeposit) {
        return depositRepository.save(theDeposit);
    }

    @Override
    public void setDeposit(BigInteger accountNo, BigInteger sendAccountNo, BigInteger receiveAccountNo, BigInteger amount) {
        Deposit theDeposit = new Deposit();
        theDeposit.setAccountNo(accountNo);
        theDeposit.setSendAccountNo(sendAccountNo);
        theDeposit.setReceiveAccountNo(receiveAccountNo);
        theDeposit.setAmount(amount);
        theDeposit.setStatus(1);
        theDeposit.setReceiveDate(new Date(System.currentTimeMillis()));
        depositRepository.save(theDeposit);
    }
}

