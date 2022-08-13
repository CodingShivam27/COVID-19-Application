package com.cowin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cowin.module.Member;
import com.cowin.utils.AdharCard;
import com.cowin.utils.PanCard;

@Repository
public interface MemberDao extends JpaRepository<Member, Integer>{
	
	public Optional<Member> findByMobileno(String string);
	
	public Optional<Member> findByAdharcard(AdharCard adharcard);
	
	public Optional<Member> findByPancard(PanCard pancard);

}
