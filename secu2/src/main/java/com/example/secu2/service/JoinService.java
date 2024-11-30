package com.example.secu2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.secu2.dto.MemberDto;
import com.example.secu2.entity.Member;
import com.example.secu2.repository.MemberRepository;

@Service
public class JoinService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private MemberRepository memberRepository;
	
	public Member regist(MemberDto memberDto) {
		Member member = new Member();
		member.setUsername(memberDto.getUsername());
		String newPw = bCryptPasswordEncoder.encode(memberDto.getPassword());
		member.setPassword(newPw);
		member.setName(memberDto.getName());
		member.setEmail(memberDto.getEmail());
		
		if(memberDto.getUsername().equals("admin")) {
			member.setRole("ROLE_ADMIN");
		}else {
			member.setRole("ROLE_MEMBER");
		}
		
		return memberRepository.save(member);
	}
}
