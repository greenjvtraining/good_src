package com.example.xyz2.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.xyz2.domain.Member;

@Mapper
public interface IMemberDao {

	public int regist(@Param("member")Member member);
	public Member findByUsername(String username);
}
