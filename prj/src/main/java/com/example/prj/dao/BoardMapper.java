package com.example.prj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.prj.dto.BoardDto;

@Mapper
public interface BoardMapper {
	
	public int insert(@Param("board") BoardDto boardDto);
	public List<BoardDto> getList();
	public BoardDto getBoard(int bno);
}
