package service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dao.IReplyDao;
import dto.ReplyDto;
import util.MybatisSqlSessionFactory;

public class ReplyService {
	private SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
	private SqlSession session = sqlSessionFactory.openSession(true);
	private IReplyDao replyDao = session.getMapper(IReplyDao.class);
	
	public void saveReply(@Param("reply") ReplyDto reply) {
		replyDao.saveReply(reply);
	}
	
	public List<ReplyDto> getReplyList(int nno){
		
		return replyDao.getReplyList(nno);
	}
}
