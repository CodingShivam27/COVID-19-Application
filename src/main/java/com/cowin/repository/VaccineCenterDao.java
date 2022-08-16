package com.cowin.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cowin.module.VaccineCenter;


@Repository
public interface VaccineCenterDao extends JpaRepository<VaccineCenter, Integer> {
			
	public VaccineCenter findByDate(LocalDate date);

	public VaccineCenter findByVacCenterName(String vacCenterName);
	
}
