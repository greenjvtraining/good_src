package com.example.secu4.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.secu4.entity.Member;

@Mapper
public interface IMemberDao {

	public int regist(@Param("member")Member member);
	
	public Member findByUsername(String username);
}
