package com.cowin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cowin.model.VaccineCenter;
import com.cowin.service.VaccineCenterService;

@RestController
@RequestMapping("/admin")
public class VaccineCenterAdmin {
	
	@Autowired
	private VaccineCenterService vaccinecenterservice;
	
	@GetMapping("/centers")
	public List<VaccineCenter> getVaccinecenter() {		
		
		return vaccinecenterservice.getAllVaccineCenter();
	}
	
	@PostMapping("/savecenter")
	public VaccineCenter savecenter(@RequestBody VaccineCenter vac) {
		return vaccinecenterservice.saveVaccineCenter(vac, null, null);
	}
	
}
