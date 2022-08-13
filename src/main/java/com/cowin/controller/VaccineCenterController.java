package com.cowin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cowin.module.VaccineCenter;
import com.cowin.service.VaccineCenterService;

@RestController
public class VaccineCenterController {
	
	@Autowired
	private VaccineCenterService vaccinecenterservice;
	
	@PostMapping("/save")
	public VaccineCenter saveVaccinecenter(@RequestBody VaccineCenter vaccinecenter) {

		return vaccinecenterservice.saveVaccineCenter(vaccinecenter);
	}
	
}
