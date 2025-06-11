package com.green.jpa_em.service;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.jpa_em.entity.Member;
import com.green.jpa_em.repository.MemberRepository;

@SpringBootTest
class MemberServiceTest {

	@Autowired
	MemberRepository memberRepository;
	
	@Test
	void testInsertDummies() {
		
		IntStream.rangeClosed(1, 3).forEach(i -> {
			Member member = Member.builder()
					.username("user" + i)
					.email("user" + i + "@green.com")
					.age(20 + i)
					.build();
			
			memberRepository.save(member);
			
		});
		
	}

}
