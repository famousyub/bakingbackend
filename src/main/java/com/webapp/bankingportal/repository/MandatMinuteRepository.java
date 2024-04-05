package com.webapp.bankingportal.repository;

import com.webapp.bankingportal.entity.Customer;
import com.webapp.bankingportal.entity.MandatMinute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MandatMinuteRepository  extends JpaRepository<MandatMinute,Long> {
}
