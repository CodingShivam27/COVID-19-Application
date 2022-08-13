package com.cowin.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cowin.exceptions.MemberNotFoundException;
import com.cowin.module.CurrentMemberSession;
import com.cowin.module.Member;
import com.cowin.repository.MemberDao;
import com.cowin.repository.SessionRepo;

@Component
public class CurrentLogInMemberImpl implements CurrentLogInMember{
	
	@Autowired
	private MemberDao memberDAO;
	
	@Autowired
	private SessionRepo sessionREPO;

	@Override
	public CurrentMemberSession getCurrentMemberSession(String key) {
		Optional<CurrentMemberSession> opt = sessionREPO.findBySessionKey(key);
		
		if(!opt.isPresent()) throw new MemberNotFoundException("");
		
		return opt.get();
	}

	@Override
	public Integer getCurrentMemberSessionId(String key) {
		Optional<CurrentMemberSession> opt = sessionREPO.findBySessionKey(key);
		
		if(!opt.isPresent()) throw new MemberNotFoundException("");
		
		return opt.get().getCurMid();
	}

	@Override
	public Member getCurrentMember(String key) {
		
		Optional<CurrentMemberSession> opt = sessionREPO.findBySessionKey(key);
		
		if(!opt.isPresent()) throw new MemberNotFoundException("");
		
		return memberDAO.getById(opt.get().getMemberId());

	}

}
