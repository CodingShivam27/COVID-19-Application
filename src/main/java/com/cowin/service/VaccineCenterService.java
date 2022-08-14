package com.cowin.service;

import java.time.LocalDate;
import java.util.List;

import com.cowin.module.VaccineCenter;

public interface VaccineCenterService {
	
	public VaccineCenter getVaccineCenterById(Integer id);
	
	public List<VaccineCenter> getAllVaccineCenter();
	
//	public VaccineCenter getVaccineCenterByCity(String city);
	
	public VaccineCenter getVaccineCenterByDate(LocalDate date);
	
	public VaccineCenter saveVaccineCenter(VaccineCenter center, String admin, String pass);
	
	public VaccineCenter updateVaccineCenter(VaccineCenter center, String admin, String pass);
	
	public Boolean deleteVaccineCenter(VaccineCenter center, String admin, String pass);
	
	
}
