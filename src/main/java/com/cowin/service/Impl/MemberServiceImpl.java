package com.cowin.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cowin.exceptions.MemberAlreadyExistException;
import com.cowin.exceptions.MemberNotFoundException;
import com.cowin.module.CurrentMemberSession;
import com.cowin.module.Member;
import com.cowin.repository.MemberDao;
import com.cowin.service.MemberSerive;
import com.cowin.utils.AdharCard;
import com.cowin.utils.CurrentLogInMember;
import com.cowin.utils.PanCard;

@Service
public class MemberServiceImpl implements MemberSerive {

	@Autowired
	private MemberDao memDao;
	
	@Autowired
	private CurrentLogInMember curMem;
	
	@Autowired
	private CurrentMemberSession currentMemberSession;

	@Override
	public Member saveMember(Member member) {

		Optional<Member> opt = memDao.findByMobileno(member.getMobileno());

		if (opt.isPresent())
			throw new MemberAlreadyExistException("Member already exists..");

		return memDao.save(member);
	}

	@Override
	public Member updatemember(Member member, String key) {
		
		Optional<Member> opt = memDao.findByMobileno(member.getMobileno());
		
		if(!opt.isPresent()) throw new MemberNotFoundException("Member does not exists..");
		
		if(currentMemberSession.getSessionKey() == key) {
			memDao.save(member);
		}
		
		throw new UnAuthorizedPerson("Unauthorized person..");
		
		
	}

	@Override
	public Boolean deletemember(Member member, String key) {
		
		Optional<Member> opt = memDao.findByMobileno(member.getMobileno());
		
		if(!opt.isPresent()) throw new MemberNotFoundException("Member does not exists..");
		
		if(currentMemberSession.getSessionKey() == key) {
			memDao.delete(member);
			return true;
		}
		
		throw new UnAuthorizedPerson("Unauthorized person..");
		
	}

	@Override
	public Member getMemberByMemberId(Integer id) {

		Optional<Member> opt = memDao.findById(id);
		
		return opt.orElseThrow(() -> new MemberNotFoundException("Member does not exists.."));
		
	}

	@Override
	public Member getMemberByMobileNo(String moblieNo) {

		Optional<Member> opt = memDao.findByMobileno(moblieNo);
		
		return opt.orElseThrow(() -> new MemberNotFoundException("Member does not exists.."));
	}

	@Override
	public Member getMemberByAdharNo(Long adharNo) {

		Optional<Member> opt = memDao.findByAdharcard(new AdharCard(adharNo));
		
		return opt.orElseThrow(() -> new MemberNotFoundException("Member does not exists.."));
	}

	@Override
	public Member getMemebrByPanNo(String panNo) {
		
		Optional<Member> opt = memDao.findByPancard(new PanCard(panNo));
		
		return opt.orElseThrow(() -> new MemberNotFoundException("Member does not exists.."));
	}

}
