package com.cowin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cowin.exceptions.VaccineCenterNotFound;
import com.cowin.model.VaccineCenter;
import com.cowin.repository.VaccineCenterDao;

@Service
public class VaccineCenterServiceImpl implements VaccineCenterService {

	@Autowired
	private VaccineCenterDao vaccinecenterdao;

	@Override
	public VaccineCenter saveVaccineCenter(VaccineCenter center, String admin, String pass) {

		return vaccinecenterdao.save(center);
	}

	@Override
	public VaccineCenter getVaccineCenterById(Integer id) {
		Optional<VaccineCenter> opt = vaccinecenterdao.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new VaccineCenterNotFound("Center Not Found");
		}

	}

	@Override
	public List<VaccineCenter> getAllVaccineCenter() {
		List<VaccineCenter> list = vaccinecenterdao.findAll();
		if (list == null) {
			throw new VaccineCenterNotFound("No Center Found");
		}
		return list;
	}

	@Override
	public VaccineCenter getVaccineCenterByCenterName(String vacineCenterName) {

		VaccineCenter vac = vaccinecenterdao.findByVacCenterName(vacineCenterName);
		if (vac == null) {
			throw new VaccineCenterNotFound("No Center Avaliable with this Name " + vacineCenterName);
		}
		return vac;
	}

	@Override
	public VaccineCenter updateVaccineCenter(VaccineCenter center, String admin, String pass) {
		Optional<VaccineCenter> opt = vaccinecenterdao.findById(center.getCenter_id());
		if (opt.isEmpty()) {
			throw new VaccineCenterNotFound("Center Not Available to Update");
		}
		return vaccinecenterdao.save(center);
	}

	@Override
	public Boolean deleteVaccineCenter(VaccineCenter center, String admin, String pass) {
		Optional<VaccineCenter> opt = vaccinecenterdao.findById(center.getCenter_id());
		if (opt.isEmpty()) {
			throw new VaccineCenterNotFound("Center Not Available to Delete");
		}
		vaccinecenterdao.delete(center);
		return true;
	}

}
