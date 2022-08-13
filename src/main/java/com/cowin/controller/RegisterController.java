package com.cowin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cowin.exceptions.MemberAlreadyExistException;
import com.cowin.module.Member;
import com.cowin.module.MemberDTO;
import com.cowin.service.MemberLoginService;
import com.cowin.service.MemberSerive;

@RestController
public class RegisterController {

	@Autowired
	private MemberLoginService memberLogin;
	
	@Autowired
	private MemberSerive memService;
	
	@PostMapping("/register")
	public String registerMember(@Valid @RequestBody Member memR) {
		
		Member newMem = memService.saveMember(memR);
		
		if(newMem!=null) return "Register succeefully";
		
		throw new MemberAlreadyExistException("LOL");
		
	}

	@PostMapping("/login")
	public String logInMember(@Valid @RequestBody MemberDTO memberDTO) {
		return memberLogin.logIntoAccount(memberDTO);
	}

	@PatchMapping("/logout")
	public String logOutMember(@RequestParam(required = false) String key) {
		return memberLogin.logOutFromAccount(key);
	}

}
