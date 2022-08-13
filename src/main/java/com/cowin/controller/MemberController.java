package com.cowin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cowin.module.Member;

@RestController
public class MemberController {

	@PutMapping("/update/key")
	public ResponseEntity<Member> updatemember(){
		
	}
}
