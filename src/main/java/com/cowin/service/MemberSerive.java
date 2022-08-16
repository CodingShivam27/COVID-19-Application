package com.cowin.service;

import java.util.List;

import com.cowin.exceptions.MemberAlreadyExistException;
import com.cowin.exceptions.MemberNotFoundException;
import com.cowin.exceptions.UnAuthorizedPerson;
import com.cowin.module.Member;
import com.cowin.module.MemberDTO;
import com.cowin.module.VaccineCenterDTO;

public interface MemberSerive {
	
	public String logIntoAccount(MemberDTO memberDto) throws RuntimeException;
	
	public String logOutFromAccount(String mobileNo, String key) throws RuntimeException;

	public Member saveMember(Member member) throws MemberAlreadyExistException;

	public Member updatemember(Member member) throws MemberAlreadyExistException;

	public String deletemember(Member member, String key) throws MemberNotFoundException, UnAuthorizedPerson ;

	public List<Member> getMemberByMobileNo(String moblieNo, String key) throws MemberNotFoundException, UnAuthorizedPerson;
	
	public VaccineCenterDTO getVaccineCenterByCenterName(String vacineCenterName);

	public List<VaccineCenterDTO> getAllVaccineCenter();

}
