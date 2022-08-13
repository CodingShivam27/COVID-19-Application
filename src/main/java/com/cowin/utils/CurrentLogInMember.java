package com.cowin.utils;

import com.cowin.module.CurrentMemberSession;
import com.cowin.module.Member;

public interface CurrentLogInMember {
	
	public CurrentMemberSession getCurrentMemberSession(String key);

	public Integer getCurrentMemberSessionId(String key);

	public Member getCurrentMember(String key);
}
