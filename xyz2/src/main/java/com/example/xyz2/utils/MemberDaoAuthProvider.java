package com.example.xyz2.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberDaoAuthProvider {
	@Autowired
	AuthSession session;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	MemberDetailsService memberDetailsService;
	
	public MemberDetails getMemberDetails(String username) {
		MemberDetails memberDetails = memberDetailsService.loadMemberByUsername(username);
		
		return memberDetails;
	}
	
	public boolean loginCheck(String username, String inputPassword) {
		MemberDetails memberDetails = getMemberDetails(username);
		System.out.println(memberDetails.getUsername());
		
		String pw = memberDetails.getPassword();
		char temp = pw.charAt(pw.length()-1);
		String verifiedKey = "" + temp;
		String verifiedPw = passwordEncoder.passwordEncoder(inputPassword, Integer.parseInt(verifiedKey));
		
		System.out.println("저장된 pw : " + pw);
		System.out.println("입력한 pw : " + verifiedPw);
		
		if(pw.equals(verifiedPw)) {
			System.out.println("login OK!!!");
			session.setAttribute(username, memberDetails);
			System.out.println(session.getAttribute(username).getRole());
			return true;
		}
		
		return false;
	}
	
}
