package com.webapp.bankingportal.repository;

import com.webapp.bankingportal.entity.Send;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SendRepository extends JpaRepository<Send, Long> {
}
