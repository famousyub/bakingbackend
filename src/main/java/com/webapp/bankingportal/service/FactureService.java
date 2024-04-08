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
import java.util.stream.Collectors;



@Service
public class FactureService  {

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private DashboardService dashboardService;


    @Autowired
    private FactureHistoryRepository factureHistoryRepository;


    public List<Facture> getall() {

        Account account  = accountRepository.findByAccountNumber(LoggedinUser.getAccountNumber());


        List<Facture> factures = factureRepository.findAll();
                factures.stream()
                .filter(er->
            er.getAccountNumber().equals( account.getAccountNumber())


        ).map(Facture::getClass)
                .collect(Collectors.toList());

                return  factures;
    }


    public Facture getById(Long id) {
        return factureRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"ressource noy found"));
    }


    public Facture save(Facture facture) {

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
            factureHistoryRepository.save(factureHistory);
            return factureRepository.save(facture);
        }

        return fac;
    }


    public void update(Long type3, Object type1) {

    }


    public void delete(Long type) {

    }
}
