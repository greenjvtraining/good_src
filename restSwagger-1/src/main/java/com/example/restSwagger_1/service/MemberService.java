package com.example.restSwagger_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.restSwagger_1.dto.MemberDto;
import com.example.restSwagger_1.entity.Member;
import com.example.restSwagger_1.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private MemberRepository memberRepository;
	
	public void addMember(MemberDto memberDto) {
		Member member = new Member();
		String encodedPw = bCryptPasswordEncoder.encode(memberDto.getPassword());
		String role = "ROLE_MEMBER";
		
		member.setUsername(memberDto.getUsername());
		member.setPassword(encodedPw);
		member.setName(memberDto.getName());
		member.setRole(role);
		
		memberRepository.save(member);
	}
}
