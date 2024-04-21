package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.MemberDto;

public interface IMemberDao {
	public List<MemberDto> getMemberList();
	public void saveMember(@Param(value = "member") MemberDto member);
}
