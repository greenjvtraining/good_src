package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.ReplyDto;

public interface IReplyDao {
	
	public void saveReply(@Param("reply") ReplyDto reply);
	public List<ReplyDto> getReplyList(int nno);
	
}
