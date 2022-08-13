package com.cowin.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cowin.module.Vaccine;
import com.cowin.module.VaccineCenter;
import com.cowin.repository.VaccineCenterDao;
import com.cowin.repository.VaccineDao;
import com.cowin.service.VaccineCenterService;

@Service
public class VaccineCenterServiceImpl implements VaccineCenterService {
	
	@Autowired
	private VaccineCenterDao vaccinecenterdao;
	
	@Autowired
	private VaccineDao vacDao;

	
	@Override
	public VaccineCenter saveVaccineCenter(VaccineCenter center) {
		
		return vaccinecenterdao.save(center);
	}

}
