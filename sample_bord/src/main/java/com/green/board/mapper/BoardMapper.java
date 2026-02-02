package com.green.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.green.board.BoardDTO;

@Mapper
public interface BoardMapper {
	
	// 게시글 추가 => insert
	public int insertBoard(BoardDTO bdto);
	
	// 게시글 전체 출력
	public List<BoardDTO> allSelect();
	
	// 한사람의 정보를 검색
	public BoardDTO oneSelect(int id);
	
	// 한 사람의 데이터를 삭제하는 메서드
	public int oneDelete(int id);
	
	// 게시글 수정하는 메서드
	public int modBoard(BoardDTO bdto);
	
	// 게시글을 검색하는 메서드
	public List<BoardDTO> searchBoard(@Param("searchType") String searchType, @Param("searchKeyword") String searchKeyword);
	
}
