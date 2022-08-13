package com.cowin.service;

import com.cowin.module.Member;

public interface MemberSerive {
	
	public Member saveMember(Member member);
	
	public Member updatemember(Member member, String key);
	
	public Boolean deletemember(Member member, String key);
	
	public Member getMemberByMemberId(Integer id);
	
	public Member getMemberByMobileNo(String moblieNo);
	
	public Member getMemberByAdharNo(Long adharNo);
	
	public Member getMemebrByPanNo(String panNo);
	
}
