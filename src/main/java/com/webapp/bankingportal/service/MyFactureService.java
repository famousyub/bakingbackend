package com.webapp.bankingportal.service;

import com.webapp.bankingportal.entity.Facture;

import java.util.List;
import java.util.Optional;

public interface MyFactureService {

    List<Facture> getAllEmployees();

    Optional<Facture> getEmployeeById(Long factid);

    Facture createEmployee(Facture facture);

    Optional<Facture> updateEmployee(Long factureid, Facture facturedetails);

    boolean deleteEmployee(Long factureid);
}
