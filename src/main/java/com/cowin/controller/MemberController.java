package com.cowin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cowin.module.Member;
import com.cowin.service.MemberSerive;

@RestController
public class MemberController {
	
	@Autowired
	private MemberSerive memberService;

	@PutMapping("/update/{key}")
	public ResponseEntity<Member> updateMember(@RequestBody Member member, @PathVariable String key){
		
		Member updatedMember = memberService.updatemember(member, key);
		
		return new ResponseEntity<Member>(updatedMember,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{key}")
	public ResponseEntity<Boolean> deleteMember(@RequestBody Member member, @PathVariable String key){
		
		Boolean deleted = memberService.deletemember(member, key);
		
		return new ResponseEntity<Boolean>(deleted,HttpStatus.OK);
	}
	
	@GetMapping("/getmeber/{id}")
	public ResponseEntity<Member> getMemberById(@PathVariable Integer id){
		
		Member mem = memberService.getMemberByMemberId(id);
		
		return new ResponseEntity<Member>(mem,HttpStatus.OK);
	}
	
	
	@GetMapping("/getmeber/{adharNo}")
	public ResponseEntity<Member> getMemberByAdharNum(@PathVariable Long adharNo){
		
		Member mem = memberService.getMemberByAdharNo(adharNo);
		
		return new ResponseEntity<Member>(mem,HttpStatus.OK);
	}
	
	@GetMapping("/getmeber/{panNo}")
	public ResponseEntity<Member> getMemberByPanNum(@PathVariable String panNo){
		
		Member mem = memberService.getMemebrByPanNo(panNo);
		
		return new ResponseEntity<Member>(mem,HttpStatus.OK);
	}
	
	@GetMapping("/getmeber/{mobileNo}")
	public ResponseEntity<Member> getMemberByMobileNum(@PathVariable String mobileNo){
		
		Member mem = memberService.getMemberByMobileNo(mobileNo);
		
		return new ResponseEntity<Member>(mem,HttpStatus.OK);
	}
}
