package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dao.IBoardDao;
import dto.BoardDto;
import util.MybatisSqlSessionFactory;

public class BoardService {
	
	//BoardDao boardDao = new BoardDao();
	private SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
	
	private SqlSession session = sqlSessionFactory.openSession(true);
	private IBoardDao boardDao = session.getMapper(IBoardDao.class);
	
	public List<BoardDto> getList(){
		//List<BoardDto> boardList = boardDao.getList();
		
		return boardDao.getBoardList();
	}
	
	public void saveBoard(BoardDto board) {
		boardDao.saveBoard(board);
	}
	
	public BoardDto getBoard(int bno) {
		return boardDao.getBoard(bno);
	}
}
