package com.cowin.service.Impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cowin.module.VaccineCenter;
import com.cowin.repository.VaccineCenterDao;
import com.cowin.service.VaccineCenterService;

@Service
public class VaccineCenterServiceImpl implements VaccineCenterService {
	
	@Autowired
	private VaccineCenterDao vaccinecenterdao;

	
	@Override
	public VaccineCenter getVaccineCenterById(Integer id) {
		Optional<VaccineCenter> opt = vaccinecenterdao.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			throw new VaccineCenterNotFound("Center Not Found");
		}

	}


	@Override
	public List<VaccineCenter> getAllVaccineCenter() {
		List<VaccineCenter> list = vaccinecenterdao.findAll();
		if(list==null) {
			throw new VaccineCenterNotFound("No Center Found");
		}
		return list;
	}


//	@Override
//	public VaccineCenter getVaccineCenterByCity(String city) {
//		VaccineCenter vac = vaccinecenterdao.findByCenter_address(city);
//		if(vac==null) {
//			throw new VaccineCenterNotFound("No Center Avaliable with this Address "+city);
//		}
//		return vac;
//	}


	@Override
	public VaccineCenter getVaccineCenterByDate(LocalDate date) {
		VaccineCenter vac = vaccinecenterdao.findByDate(date);
		if(vac==null) {
			throw new VaccineCenterNotFound("No Center Avaliable on that date "+date);
		}
		return vac;
	}

	@Override
	public VaccineCenter saveVaccineCenter(VaccineCenter center, String admin, String pass) {
		
		return vaccinecenterdao.save(center);
	}
	
	@Override
	public VaccineCenter updateVaccineCenter(VaccineCenter center,String admin, String pass) {
		Optional<VaccineCenter> opt = vaccinecenterdao.findById(center.getCenter_id());
		if(opt.isEmpty()) {
			throw new VaccineCenterNotFound("Center Not Available to Update");
		}
		return vaccinecenterdao.save(center);
	}


	@Override
	public Boolean deleteVaccineCenter(VaccineCenter center,String admin, String pass) {
		Optional<VaccineCenter> opt = vaccinecenterdao.findById(center.getCenter_id());
		if(opt.isEmpty()) {
			throw new VaccineCenterNotFound("Center Not Available to Delete");
		}
		vaccinecenterdao.delete(center);
		return true;
	}


}
