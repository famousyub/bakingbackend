package com.webapp.bankingportal.repository;

import com.webapp.bankingportal.entity.CreditComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface  CreditCommentRepository extends JpaRepository<CreditComment,Long> {


    Page<CreditComment> findByCreditId(Long postId, Pageable pageable);
    Optional<CreditComment> findByIdAndCreditId(Long id, Long postId);

}
