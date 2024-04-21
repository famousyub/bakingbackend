package com.webapp.bankingportal.dao;


import com.webapp.bankingportal.entity.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppointmentDao extends CrudRepository<Appointment, Long> {

    List<Appointment> findAll();
}