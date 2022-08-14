package com.cowin.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cowin.exceptions.MemberAlreadyExistException;
import com.cowin.exceptions.MemberNotFoundException;
import com.cowin.exceptions.UnAuthorizedPerson;
import com.cowin.module.CurrentMemberSession;
import com.cowin.module.Member;
import com.cowin.repository.MemberDao;
import com.cowin.service.MemberSerive;
import com.cowin.utils.AdharCard;
import com.cowin.utils.PanCard;

@Service
public class MemberServiceImpl implements MemberSerive {

	@Autowired
	private MemberDao memDao;

	@Autowired(required = false)
	private CurrentMemberSession currentMemberSession;

	@Override
	public Member saveMember(Member member) throws MemberAlreadyExistException{

		Optional<Member> opt = memDao.findByMobileno(member.getMobileno());
		
		if (opt.isPresent()) {
			Member existinMem = opt.get();
			if (existinMem.getAdharcard().getAdharNo().equals(member.getAdharcard().getAdharNo()))
				throw new MemberAlreadyExistException(
						"Member already exists with adharNo: " + member.getAdharcard().getAdharNo());	
			else if(existinMem.getPancard().getPanNo().equals(member.getPancard().getPanNo())) 
				throw new MemberAlreadyExistException(
						"Member already exists with panNo: " + member.getPancard().getPanNo());
			else
				return memDao.save(member);
		}
		else
		return memDao.save(member);
	}

	@Override
	public Member updatemember(Member member, String key)throws MemberNotFoundException, UnAuthorizedPerson{

		Optional<Member> opt = memDao.findByMobileno(member.getMobileno());

		if (!opt.isPresent())
			throw new MemberNotFoundException("Member does not exists with mobile no: " + member.getMobileno());

		if (currentMemberSession.getSessionKey() == key) {

			if (memDao.findByAdharcard(member.getAdharcard()).isPresent())
				throw new MemberAlreadyExistException(
						"Member already exists with adharNo: " + member.getAdharcard().getAdharNo());

			else if (memDao.findByPancard(member.getPancard()).isPresent())
				throw new MemberAlreadyExistException(
						"Member already exists with panNo: " + member.getPancard().getPanNo());

			else
				memDao.save(member);
		}

		throw new UnAuthorizedPerson("Unauthorized person..");

	}

	@Override
	public Boolean deletemember(Member member, String key) throws MemberNotFoundException, UnAuthorizedPerson{

		Optional<Member> opt = memDao.findByMobileno(member.getMobileno());

		if (!opt.isPresent())
			throw new MemberNotFoundException("Member does not exists with mobile no: " + member.getMobileno());

		if (currentMemberSession.getSessionKey() == key) {
			memDao.delete(member);
			return true;
		}

		throw new UnAuthorizedPerson("Unauthorized person..");

	}

	@Override
	public Member getMemberByMemberId(Integer id, String key) throws MemberNotFoundException, UnAuthorizedPerson{

		Optional<Member> opt = memDao.findById(id);

		if (!opt.isPresent())
			throw new MemberNotFoundException("Member does not exists..");

		if (currentMemberSession.getSessionKey() == key) {
			return opt.get();
		}

		throw new UnAuthorizedPerson("Unauthorized person..");

	}

	@Override
	public Member getMemberByMobileNo(String moblieNo, String key) throws MemberNotFoundException, UnAuthorizedPerson {

		Optional<Member> opt = memDao.findByMobileno(moblieNo);

		if (!opt.isPresent())
			throw new MemberNotFoundException("Member does not exists..");

		if (currentMemberSession.getSessionKey() == key) {
			return opt.get();
		}

		throw new UnAuthorizedPerson("Unauthorized person..");
	}

	@Override
	public Member getMemberByAdharNo(String adharNo, String key)throws MemberNotFoundException, UnAuthorizedPerson{

		Optional<Member> opt = memDao.findByAdharcard(new AdharCard(adharNo));

		if (!opt.isPresent())
			throw new MemberNotFoundException("Member does not exists..");

		if (currentMemberSession.getSessionKey() == key) {
			return opt.get();
		}

		throw new UnAuthorizedPerson("Unauthorized person..");
	}

	@Override
	public Member getMemebrByPanNo(String panNo, String key)throws MemberNotFoundException, UnAuthorizedPerson{

		Optional<Member> opt = memDao.findByPancard(new PanCard(panNo));

		if (!opt.isPresent())
			throw new MemberNotFoundException("Member does not exists..");

		if (currentMemberSession.getSessionKey() == key) {
			return opt.get();
		}

		throw new UnAuthorizedPerson("Unauthorized person..");
	}

}
