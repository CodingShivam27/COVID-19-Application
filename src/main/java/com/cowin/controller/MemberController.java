package com.cowin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cowin.module.Member;
import com.cowin.service.MemberSerive;

@RestController
@RequestMapping("/user")
public class MemberController {

	@Autowired
	private MemberSerive memberService;

	@PutMapping("/update/{key}")
	public ResponseEntity<Member> updateMember(@Valid @RequestBody Member member,
			@RequestParam(required = true, value = "key") String key) {

		Member updatedMember = memberService.updatemember(member, key);

		return new ResponseEntity<Member>(updatedMember, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{key}")
	public ResponseEntity<Boolean> deleteMember(@Valid @RequestBody Member member,
			@RequestParam(required = true, value = "key") String key) {

		Boolean deleted = memberService.deletemember(member, key);

		return new ResponseEntity<Boolean>(deleted, HttpStatus.OK);
	}

	@GetMapping("/getmeber/{key}/{id}")
	public ResponseEntity<Member> getMemberById(@PathVariable Integer id,
			@RequestParam(required = true, value = "key") String key) {

		Member mem = memberService.getMemberByMemberId(id,key);

		return new ResponseEntity<Member>(mem, HttpStatus.OK);
	}

	@GetMapping("/getmeber/{key}/{adharNo}")
	public ResponseEntity<Member> getMemberByAdharNum(@PathVariable String adharNo,
			@RequestParam(required = true, value = "key") String key) {

		Member mem = memberService.getMemberByAdharNo(adharNo,key);

		return new ResponseEntity<Member>(mem, HttpStatus.OK);
	}

	@GetMapping("/getmeber/{key}/{panNo}")
	public ResponseEntity<Member> getMemberByPanNum(@PathVariable String panNo,
			@RequestParam(required = true, value = "key") String key) {

		Member mem = memberService.getMemebrByPanNo(panNo,key);

		return new ResponseEntity<Member>(mem, HttpStatus.OK);
	}

	@GetMapping("/getmeber/{key}/{mobileNo}")
	public ResponseEntity<Member> getMemberByMobileNum(@PathVariable String mobileNo,
			@RequestParam(required = true, value = "key") String key) {

		Member mem = memberService.getMemberByMobileNo(mobileNo,key);

		return new ResponseEntity<Member>(mem, HttpStatus.OK);
	}
}
