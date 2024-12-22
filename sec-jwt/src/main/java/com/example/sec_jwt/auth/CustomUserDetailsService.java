package com.example.sec_jwt.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.sec_jwt.entity.Member;
import com.example.sec_jwt.repository.MemberRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("username : " + username);
		
		Member member = memberRepository.findByUsername(username);
		
		System.out.println("Member : " + member);
		
		if(member != null) {
			CustomUserDetails userData = new CustomUserDetails(member);
			return userData;
		}
		return null;
	}

}
