package com.green.jpa_em.service;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.green.jpa_em.entity.Board;
import com.green.jpa_em.entity.Member;
import com.green.jpa_em.repository.BoardRepository;

@SpringBootTest
class BoardServiceTest {

	@Autowired
	BoardService boardService;
	
	@Autowired
	BoardRepository boardRepository;
	
	//@Test
	//@Transactional
	/*
	 * Spring Boot Test에서 @Transactional이 적용된 테스트 메서드는 기본적으로 실행 후 자동으로 롤백됩니다. 
	 * 따라서 해당 테스트에서 수행한 모든 insert, update, delete 작업은 실제 데이터베이스에 반영되지 않습니다.
	 */
	void testGetBoardList() {
		List<Board> list = boardService.getList();
		for(Board board : list) {
			System.out.println(board.getTitle() + "|" + board.getMember().getUsername());
		}
	}
	
	@Test
	//@Transactional
	void testGetBoardList2() {
		List<Board> list = boardService.getListWithMember();
		for(Board board : list) {
			System.out.println(board.getTitle() + "|" + board.getMember().getUsername());
		}
	}
	
	//@Transactional
	//@Test
	void testInsertDummies() {
		Member member = new Member();
		member.setId(2L);
		
		IntStream.rangeClosed(11, 20).forEach(i -> {
			Board board = Board.builder()
					.title("title" + i)
					.content("content" + i)
					.member(member)
					.build();
			boardRepository.save(board);
		});
		
	}

}

//롤백이 되지 않도록 설정하는 방법
//@Transactional(rollbackFor = false) 사용 (비추천)
//@Commit 어노테이션 사용 (Spring 제공)
//트랜잭션 미사용 (@Transactional 제거)
//Lazy Loading 테스트 시에는 @Transactional이 필수적이지만, Fetch Join으로 대체할 수 있습니다.
