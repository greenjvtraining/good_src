package com.example.xyz2.utils;

import com.example.xyz2.domain.Member;

public class MemberDetails {

	private Member member;
	
	public MemberDetails(Member member) {
		this.member = member;
	}
	
	public String getUsername() {
		return member.getUsername();
	}
	
	public String getPassword() {
		return member.getPassword();
	}
	
	public String getName() {
		return member.getName();
	}
	
	public String getRole() {
		return member.getRole();
	}
}
