package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberDto;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private MemberRepository memberRepository;
	
	public void regMember(MemberDto member) {
		String newPw = bCryptPasswordEncoder.encode(member.getPassword());
		
		Member entity = Member.builder()
				.username(member.getUsername())
				.password(newPw) //암호화된 패스워드
				.name(member.getName())
				.role("ROLE_MEMBER")
				.build();
		
		memberRepository.save(entity);
	}
	
	
}
