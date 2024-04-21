package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.NoticeDto;

public interface INoticeDao {
	
	public List<NoticeDto> getNoticeList();
	public NoticeDto getNotice(int nno);
	public void saveNotice(@Param("notice") NoticeDto notice);
}
