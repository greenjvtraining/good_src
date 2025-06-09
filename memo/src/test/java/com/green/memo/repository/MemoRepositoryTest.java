package com.green.memo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.memo.entity.Member;
import com.green.memo.entity.Memo;

@SpringBootTest
class MemoRepositoryTest {

	@Autowired
	MemoRepository memoRepository;
	
	@Test
	void testSelectAll2() {
		List<Memo> memoList = memoRepository.findAllWithMember(); //join fetch는 JPQL 전용 문법!
		//기본 FetchType을 바꾸는 게 아니라, 쿼리에서만 강제로 fetch join!
		for(Memo memo : memoList) {
			System.out.println(memo.getMno() + " : " + memo.getMemoText() + " - " + memo.getMember().getMember_id() + ", " + memo.getMember().getUsername());
		}
		
	}
	
	//@Transactional
	//@Test
	void testSelectAll() {
		List<Memo> memoList = memoRepository.findAll(); //Memo만 가져오고, m.member는 프록시. 나중에 호출 시 또 쿼리.
		
		for(Memo memo : memoList) {
			System.out.println(memo.getMno() + " : " + memo.getMemoText() + " - " + memo.getMember().getMember_id() + ", " + memo.getMember().getUsername());
		}
		
	}
	
	//@Test
	void testInsert() {
		Member member = new Member();
		member.setMember_id(2);
		
		Memo memo = Memo.builder()
				.memoText("hello WORLD~~1")
				.member(member)
				.level("NORMAL")
				.build();
		
		Memo result = memoRepository.save(memo);
		System.out.println(result);
	
	}

}
