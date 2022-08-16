package com.cowin.service;

import java.util.List;

import com.cowin.model.VaccineCenter;

public interface VaccineCenterService {
	
	public VaccineCenter getVaccineCenterById(Integer id);
	
	public List<VaccineCenter> getAllVaccineCenter();
	
	public VaccineCenter getVaccineCenterByCenterName(String vacineCenterName);
	
	public VaccineCenter saveVaccineCenter(VaccineCenter center, String admin, String pass);
	
	public VaccineCenter updateVaccineCenter(VaccineCenter center, String admin, String pass);
	
	public Boolean deleteVaccineCenter(VaccineCenter center, String admin, String pass);
		
}
