package com.cowin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cowin.module.AppoinmetDTO;
import com.cowin.module.Appointment;
import com.cowin.module.Member;
import com.cowin.module.MemberDTO;
import com.cowin.service.AppointmentService;
import com.cowin.service.MemberSerive;

@RestController
@RequestMapping("/user")
public class MemberController {

	@Autowired
	private MemberSerive memberService;

	@Autowired
	private AppointmentService appService;

	@Autowired
	private MemberSerive memService;

	@PostMapping("/register")
	public ResponseEntity<Member> registerMember(@Valid @RequestBody Member memR) {

		Member newMem = memService.saveMember(memR);

		return new ResponseEntity<Member>(newMem, HttpStatus.CREATED);

	}

	@PostMapping("/login")
	public String logInMember(@Valid @RequestBody MemberDTO memberDTO) {

		return memService.logIntoAccount(memberDTO);
	}

	@PatchMapping("/logout/{mobileNo}")
	public String logOutMember(@RequestParam(required = false) String key, @PathVariable("mobileNo") String mobileNo) {

		return memService.logOutFromAccount(mobileNo, key);
	}

	@PutMapping("/update/{key}")
	public ResponseEntity<Member> updateMember(@Valid @RequestBody Member member) {

		Member updatedMember = memberService.updatemember(member);

		return new ResponseEntity<Member>(updatedMember, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{mobileNo}")
	public ResponseEntity<String> deleteMember(@Valid @RequestBody Member member,
			@RequestParam(required = true, value = "key") String key) {

		String response = memberService.deletemember(member, key);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@GetMapping("/getmeber/{mobileNo}")
	public ResponseEntity<List<Member>> getMemberByMobileNum(@PathVariable("mobileNo") String mobileNo,
			@RequestParam(required = true) String key) {

		List<Member> memList = memberService.getMemberByMobileNo(mobileNo, key);

		return new ResponseEntity<List<Member>>(memList, HttpStatus.OK);
	}

	@PostMapping("/bookappointment")
	public ResponseEntity<AppoinmetDTO> bookAppointment(@Valid @RequestBody AppoinmetDTO appDto) {

		AppoinmetDTO dto = appService.addAppointMentDetails(appDto);

		return new ResponseEntity<AppoinmetDTO>(dto, HttpStatus.CREATED);

	}

	@DeleteMapping("/cancelappointment")
	public ResponseEntity<String> cancelAppointment(@RequestBody AppoinmetDTO appDto) {

		String response = appService.deleteBookedTrip(appDto);

		return new ResponseEntity<String>(response, HttpStatus.OK);

	}

	@PostMapping("/appointmentdetails")
	public ResponseEntity<List<Appointment>> getAllAppointment(@RequestBody AppoinmetDTO appDto) {

		List<Appointment> appointments = appService.getAllAppointmentsOfMember(appDto);

		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);

	}

}
