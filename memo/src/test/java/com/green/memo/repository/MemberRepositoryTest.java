package com.green.memo.repository;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.memo.entity.Member;

@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;
	
	//@Test
	public void testSelectAll() {
		
	}
	
	
	//@Test
	public void testDelete() {
		Integer mno = 5;
		
		memberRepository.deleteById(mno);
	}
	
	
	//@Test
	public void testSelect3() {
		Integer mno = 30;
		try {
		    Member member = memberRepository.findById(mno)
		        .orElseThrow(() -> new NoSuchElementException("회원이 존재하지 않습니다."));
		    System.out.println(member);
		} catch (NoSuchElementException e) {
		    System.out.println(e.getMessage());  // "회원이 존재하지 않습니다."
		}
	}
	
	//@Test
	public void testSelect2() {
		Integer mno = 20;
		
		memberRepository.findById(mno).ifPresentOrElse(
				member -> System.out.println(member), 
				() -> System.out.println("해당 회원을 찾을 수 없습니다."));
	}
	
	//@Test
	public void testSelect1() {
		Integer mno = 2;
		
		Optional<Member> result = memberRepository.findById(mno);
		
		System.out.println("========================================");
		
		if(result.isPresent()) {
			Member member = result.get();
			System.out.println(member);
		} else {
		    System.out.println("해당 회원을 찾을 수 없습니다.");
		}
	}
	
	//@Transactional
	//@Test
	public void testSelect0() {
		Integer mno = 2;
		
		Member member = memberRepository.getOne(mno);
		System.out.println("===================================");
		System.out.println(member);
	}
	
	//@Test
	public void testUpdate() {
		/*
		Member member = Member.builder()
				.mno(1)
				.username("user0")
				.password("4444")
				.build();
		*/
		Member member = memberRepository.findById(1).orElseThrow();
		member.setPassword("123456");
		member.setName("user0");
		member.setPhone("010-1234-5678");
		memberRepository.save(member);
	}
	
	@Test
	public void testInsertDummies() {
		IntStream.rangeClosed(0, 3).forEach(i -> {
			Member member = Member.builder()
					.username("user" + i)
					.password("1234" + i)
					.name("user" + i)
					.phone("010-1111-123" + i)
					.build();
			memberRepository.save(member);
		});
	}
	
	//@Test
	void test() {
		System.out.println(memberRepository.getClass().getName());
	}

}
