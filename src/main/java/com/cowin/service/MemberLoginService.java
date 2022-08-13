package com.cowin.service;

import com.cowin.module.MemberDTO;

public interface MemberLoginService {

	public String logIntoAccount(MemberDTO memberDto) throws RuntimeException;
	
	public String logOutFromAccount(String key) throws RuntimeException;

}
