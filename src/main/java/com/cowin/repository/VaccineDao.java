package com.cowin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cowin.module.Vaccine;

public interface VaccineDao extends JpaRepository<Vaccine, Integer> {
			
	public Vaccine findByVaccineName(String name);
	
	public Vaccine findByPrice(double price);
	
}
