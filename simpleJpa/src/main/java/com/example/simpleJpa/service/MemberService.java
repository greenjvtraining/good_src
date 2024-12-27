package com.example.simpleJpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.simpleJpa.dto.MemberDto;
import com.example.simpleJpa.entity.BoardEntity;
import com.example.simpleJpa.entity.MemberEntity;
import com.example.simpleJpa.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private MemberRepository memberRepository;
	
	public void regist(MemberDto memberDto) {
		MemberEntity member = memberDto.memberToEntity(memberDto);
		
		String encodedPw = bCryptPasswordEncoder.encode(memberDto.getPassword());
		member.setPassword(encodedPw);
		member.setRole("ROLE_MEMBER");
		
		memberRepository.save(member);
	}
	
	public void update(MemberDto memberDto) {
		MemberEntity member = memberDto.memberToEntity(memberDto);
		
		memberRepository.save(member);
	}
	
	public MemberEntity getMember(String username) {
		return memberRepository.findByUsername(username);
	}
	
	public List<MemberEntity> getMembers(){
		return memberRepository.findAll();
	}
	//@Transactional
	public List<BoardEntity> getMemberBoards(String usename){
		MemberEntity member = memberRepository.findByUsername(usename);
		member.getBoards().size();
		List<BoardEntity> list = member.getBoards();
		
		return list;
	}
}
