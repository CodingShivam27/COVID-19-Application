package com.cowin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cowin.module.CurrentMemberSession;

public interface CurrentMemberSessionDao extends JpaRepository<CurrentMemberSession, Integer>{

}
