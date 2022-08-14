package com.cowin.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cowin.module.VaccineCenter;

public interface VaccineCenterDao extends JpaRepository<VaccineCenter, Integer> {
			
	public VaccineCenter findByDate(LocalDate date);
	
//	public VaccineCenter findByCenter_address(String city);
}
