package com.example.sec_jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sec_jwt.dto.JoinDto;
import com.example.sec_jwt.dto.MemberDto;
import com.example.sec_jwt.entity.Member;
import com.example.sec_jwt.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private MemberRepository memberRepository;
	/*
	public boolean registMember(MemberDto memberDto) {
	
		Member member = new Member();
		member.setUsername(memberDto.getUsername());
		String newPw = bCryptPasswordEncoder.encode(memberDto.getPassword());
		member.setPassword(newPw);
		member.setRole("ROLE_USER");
		
		Member result = memberRepository.save(member);
		
		if(result != null) return true;
		
		return false;
	}
	*/
	public boolean joinProcess(JoinDto joinDto) {
		Member member = new Member();
		member.setUsername(joinDto.getUsername());
		String newPw = bCryptPasswordEncoder.encode(joinDto.getPassword());
		member.setPassword(newPw);
		member.setNickname(joinDto.getNickname());
		member.setRole("ROLE_USER");
		
		Member result = memberRepository.save(member);
		
		if(result != null) return true;
		
		return false;
	}
}
