package com.example.secu4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.secu4.dao.IMemberDao;
import com.example.secu4.entity.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService{

	//@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private IMemberDao memberDao;
	
	public int regist(Member member) {
		//패스워드 암호화
		String encodedPassword = bCryptPasswordEncoder.encode(member.getPassword());
		member.setPassword(encodedPassword);
		//롤 세팅
		member.setRole("ROLE_MEMBER");
				
		//DB 등록
		int result = memberDao.regist(member);
		
		return result;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member member = memberDao.findByUsername(username);
		
		return member;
	}
}
