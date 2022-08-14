package com.cowin.service;

import com.cowin.exceptions.MemberAlreadyExistException;
import com.cowin.exceptions.MemberNotFoundException;
import com.cowin.exceptions.UnAuthorizedPerson;
import com.cowin.module.Member;

public interface MemberSerive {

	public Member saveMember(Member member) throws MemberAlreadyExistException;

	public Member updatemember(Member member, String key) throws MemberAlreadyExistException;

	public Boolean deletemember(Member member, String key) throws MemberNotFoundException, UnAuthorizedPerson ;

	public Member getMemberByMemberId(Integer id, String key) throws MemberNotFoundException, UnAuthorizedPerson;

	public Member getMemberByMobileNo(String moblieNo, String key)throws MemberNotFoundException, UnAuthorizedPerson;

	public Member getMemberByAdharNo(String adharNo, String key) throws MemberNotFoundException, UnAuthorizedPerson;

	public Member getMemebrByPanNo(String panNo, String key) throws MemberNotFoundException, UnAuthorizedPerson;

}
