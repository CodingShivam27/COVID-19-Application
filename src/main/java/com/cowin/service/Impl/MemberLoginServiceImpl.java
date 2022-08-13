package com.cowin.service.Impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cowin.exceptions.MemberAlreadyExistException;
import com.cowin.exceptions.MemberNotFoundException;
import com.cowin.module.CurrentMemberSession;
import com.cowin.module.Member;
import com.cowin.module.MemberDTO;
import com.cowin.repository.MemberDao;
import com.cowin.repository.SessionRepo;
import com.cowin.service.MemberLoginService;
import com.cowin.utils.CurrentLogInMember;

import net.bytebuddy.utility.RandomString;

@Service
public class MemberLoginServiceImpl implements MemberLoginService{
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private SessionRepo sessionRepo;
	
	@Autowired
	private CurrentLogInMember currentLogInMember;

	@Override
	public String logIntoAccount(MemberDTO memberDto) {

		Optional<Member> opt = memberDao.findByMobileno(memberDto.getMobileno());
		
		if(!opt.isPresent()) {
			throw new MemberNotFoundException("Please Enter Valid Mobile Number");
		}
		
		Member newMember = opt.get();
		Integer memberId = newMember.getMemberId();
		Optional<CurrentMemberSession> currentUserOptional = sessionRepo.findByMemberId(memberId);
		
		if(currentUserOptional.isPresent()) {
			throw new MemberAlreadyExistException("User already logged in with this number");
		}
	
			
		String key = RandomString.make(6);
		
		CurrentMemberSession currentMemberSession = new CurrentMemberSession(newMember.getMemberId(), key, LocalDateTime.now());			
		sessionRepo.save(currentMemberSession);

		return currentMemberSession.toString();
		
	}

	@Override
	public String logOutFromAccount(String key) {

		Optional<CurrentMemberSession> currentMemberOptional = sessionRepo.findBySessionKey(key);
		
		if(!currentMemberOptional.isPresent()) {
			throw new MemberNotFoundException("User has to login first");
		}
		
		CurrentMemberSession currentMemberSession = currentLogInMember.getCurrentMemberSession(key);
		sessionRepo.delete(currentMemberSession);
		
		return "Logged Out Successfully";
	}
	
	
}
