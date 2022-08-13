package com.cowin.repository;

import java.util.Optional;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cowin.module.CurrentMemberSession;

@Repository
public interface SessionRepo extends JpaRepository<CurrentMemberSession, Id>{
	
	public Optional<CurrentMemberSession> findByMemberId(Integer memId);
	
	public Optional<CurrentMemberSession> findBySessionKey(String key);
	
}
