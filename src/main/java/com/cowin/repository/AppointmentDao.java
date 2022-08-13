package com.cowin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cowin.module.Appointment;

public interface AppointmentDao extends JpaRepository<Appointment, Integer>{

}
