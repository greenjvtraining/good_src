package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dao.INoticeDao;
import dto.NoticeDto;
import util.MybatisSqlSessionFactory;

public class NoticeService {

	private SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
	
	private SqlSession session = sqlSessionFactory.openSession(true);
	private INoticeDao noticeDao = session.getMapper(INoticeDao.class);
	
	public List<NoticeDto> getNoticeList(){
		//List<BoardDto> boardList = boardDao.getList();
		
		return noticeDao.getNoticeList();
	}
	
	public void saveNotice(NoticeDto notice) {
		noticeDao.saveNotice(notice);
	}
	
	public NoticeDto getNotice(int nno) {
		return noticeDao.getNotice(nno);
	}
}
