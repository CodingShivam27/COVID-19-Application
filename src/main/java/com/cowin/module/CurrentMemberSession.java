package com.cowin.module;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrentMemberSession {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer curMid;
	
	@Column(unique = true)
	private Integer memberId;
	
	private String sessionKey;
	
	private LocalDateTime localDateTime;

	public CurrentMemberSession(Integer memberId, String sessionKey, LocalDateTime localDateTime) {
		super();
		this.memberId = memberId;
		this.sessionKey = sessionKey;
		this.localDateTime = localDateTime;
	}

	
}
