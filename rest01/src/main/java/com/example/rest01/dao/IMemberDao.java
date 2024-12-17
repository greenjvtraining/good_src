package com.example.rest01.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.rest01.domain.Member;

@Mapper
public interface IMemberDao {
	
	public Member getMember(String username);
	public void regMember(@Param("member")Member member);
	public void updateMember(@Param("member")Member member);
	public void delMember(String username);
}
