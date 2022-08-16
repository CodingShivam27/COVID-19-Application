package com.cowin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cowin.module.Appointment;

@Repository
public interface AppointmentDao extends JpaRepository<Appointment, Long>{

}
