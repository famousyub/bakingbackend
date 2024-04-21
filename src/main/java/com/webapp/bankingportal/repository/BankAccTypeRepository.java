package com.webapp.bankingportal.repository;

import com.webapp.bankingportal.entity.BankAccType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BankAccTypeRepository  extends JpaRepository<BankAccType, Long> {
}
