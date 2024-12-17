package com.example.rest01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.rest01.domain.Board;

@Mapper
public interface IBoardDao {

	public List<Board> getList();
	public void regist(@Param("board") Board board);
}
