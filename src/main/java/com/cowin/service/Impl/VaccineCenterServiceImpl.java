package com.cowin.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cowin.module.Vaccine;
import com.cowin.module.VaccineCenter;
import com.cowin.repository.VaccineCenterDao;
import com.cowin.service.VaccineCenterService;
import com.cowin.service.VaccineService;

@Service
public class VaccineCenterServiceImpl implements VaccineCenterService {
	
	@Autowired
	private VaccineCenterDao vaccinecenterdao;
	

	
	@Override
	public VaccineCenter saveVaccineCenter(VaccineCenter center) {
		
		
		return vaccinecenterdao.save(center);
	}

}
