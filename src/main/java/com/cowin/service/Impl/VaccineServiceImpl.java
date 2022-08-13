package com.cowin.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cowin.module.Vaccine;
import com.cowin.repository.VaccineDao;
import com.cowin.service.VaccineService;

public class VaccineServiceImpl implements VaccineService {

	@Autowired
	private VaccineDao vaccinedao;

	@Override
	public Vaccine saveVaccine(Vaccine vaccine) {
		
		return vaccinedao.save(vaccine);
	}

}
