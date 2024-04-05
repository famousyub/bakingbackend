package com.webapp.bankingportal.repository;

import com.webapp.bankingportal.entity.ERole;
import com.webapp.bankingportal.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}

