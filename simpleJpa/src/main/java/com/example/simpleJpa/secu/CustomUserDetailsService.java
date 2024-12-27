package com.example.simpleJpa.secu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.simpleJpa.entity.MemberEntity;
import com.example.simpleJpa.repository.MemberRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("CUDS >> " + username);
		MemberEntity member = memberRepository.findByUsername(username);
		CustomUserDetails user = new CustomUserDetails(member);
		return user;
	}

}
