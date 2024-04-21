package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dao.IMemberDao;
import dto.MemberDto;
import util.MybatisSqlSessionFactory;

public class MemberService {
	
	private SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
	SqlSession session = sqlSessionFactory.openSession(true);
	IMemberDao memberDao = session.getMapper(IMemberDao.class);
	
	public List<MemberDto> getMemberList(){
		
		return memberDao.getMemberList();
	}
	
	public void saveMember(MemberDto member) {
		memberDao.saveMember(member);
	}
}
