package com.example.xyz2.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.xyz2.dao.IMemberDao;
import com.example.xyz2.domain.Member;

@Service
public class MemberDetailsService {
	
	@Autowired
	private IMemberDao memberDao;
	
	public MemberDetails loadMemberByUsername(String username) {
		Member member = memberDao.findByUsername(username);
		
		if(member != null) {
			return new MemberDetails(member);
		}
		
		return null;
	}
	
}
