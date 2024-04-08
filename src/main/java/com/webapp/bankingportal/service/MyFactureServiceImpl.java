package com.webapp.bankingportal.service;


import com.webapp.bankingportal.entity.Account;
import com.webapp.bankingportal.entity.Facture;
import com.webapp.bankingportal.entity.FactureHistory;
import com.webapp.bankingportal.repository.AccountRepository;
import com.webapp.bankingportal.repository.FactureHistoryRepository;
import com.webapp.bankingportal.repository.FactureRepository;
import com.webapp.bankingportal.util.LoggedinUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyFactureServiceImpl implements MyFactureService {

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private DashboardService dashboardService;


    @Autowired
    private FactureHistoryRepository factureHistoryRepository;
    @Override
    public List<Facture> getAllEmployees() {
        Account account  = accountRepository.findByAccountNumber(LoggedinUser.getAccountNumber());


        List<Facture> factures = factureRepository.findAll();
        factures.stream()
                .filter(er->
                        er.getAccountNumber().equals( account.getAccountNumber())


                ).map(Facture::getClass)
                .collect(Collectors.toList());

        return  factures;
    }

    @Override
    public Optional<Facture> getEmployeeById(Long factid) {
        return factureRepository.findById(factid);
                //.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"ressource noy found"));

    }

    @Override
    public Facture createEmployee(Facture facture) {
        Facture fac = new Facture();
        Account account =  accountRepository.findByAccountNumber(LoggedinUser.getAccountNumber());
        FactureHistory factureHistory = new FactureHistory();
        factureHistory.setNom(facture.getNom());
        factureHistory.setPrenom(facture.getPrenom());
        factureHistory.setPhoto(facture.getPhoto());
        factureHistory.setAccountNumber(account.getAccountNumber());
        facture.setAccountNumber(LoggedinUser.getAccountNumber());

        if(facture.getAmount()< account.getBalance())
        {
            facture.setUsername(account.getUser().getName());
           FactureHistory fg =  factureHistoryRepository.save(factureHistory);
            facture.setFactureHistory(fg);
            double i = account.getBalance()  - facture.getAmount() ;
            account.setBalance(i);
            accountRepository.save(account);
            return factureRepository.save(facture);
        }

        return fac;
    }

    @Override
    public Optional<Facture> updateEmployee(Long factureid, Facture facturedetails) {
        return Optional.empty();
    }

    @Override
    public boolean deleteEmployee(Long factureid) {

        Optional<Facture>myfacture =factureRepository.findById(factureid);

        if(myfacture.isPresent()){
            myfacture.get().setFactureHistory(null);
            factureRepository.delete(myfacture.get());
        return  true;
        }

        return false;
    }
}
