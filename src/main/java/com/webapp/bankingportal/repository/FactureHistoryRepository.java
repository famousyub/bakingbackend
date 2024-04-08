package com.webapp.bankingportal.repository;

import com.webapp.bankingportal.entity.FactureHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FactureHistoryRepository extends JpaRepository<FactureHistory,Long> {
}
