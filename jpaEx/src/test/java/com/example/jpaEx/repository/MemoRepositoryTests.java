package com.example.jpaEx.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpaEx.entity.Memo;

@SpringBootTest
public class MemoRepositoryTests {

	@Autowired
	MemoRepository memoRepository;
	
	//@Test
	public void testClass() {
		System.out.println(memoRepository.getClass().getName());
	}
	
	//@Test // insert : C -- save(엔티티)
	public void testInsertDummies() {
		
		for(int i = 0; i < 100; i++) {
			
			Memo memo = new Memo("Sample....." + i);
			memoRepository.save(memo);
		}		
	}
	
	//@Test
	public void testSelect() {
		Long mno = 100L;
		
		Optional<Memo> result = memoRepository.findById(mno);
		System.out.println("========================");
		
		if(result.isPresent()) {
			Memo memo = result.get();
			System.out.println(memo);
		}
	}
	
	//@Test
	public void testUpdate() {
		Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
		
		System.out.println(memoRepository.save(memo));
	}
	
	
	
	//@Test
	public void testDelete() {
		Long mno = 100L;
		
		memoRepository.deleteById(mno);
	}
	
	//@Test
	public void testCount() {
		
		long cnt = memoRepository.count();
		
		System.out.println("cnt : " + cnt);
		
		assertEquals(cnt, 100);
	}
	
	@Test
	public void testSelectAll() {
		List<Memo> list = memoRepository.findAll();
		
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
	
}
