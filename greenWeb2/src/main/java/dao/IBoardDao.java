package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.BoardDto;

public interface IBoardDao {
	public List<BoardDto> getBoardList();
	public void saveBoard(@Param("board") BoardDto board);
	public BoardDto getBoard(int bno); //일반자료형의 매핑
}
