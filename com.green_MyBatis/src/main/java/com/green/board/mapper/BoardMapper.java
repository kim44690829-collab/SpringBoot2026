package com.green.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.green.board.BoardDTO;

@Mapper
public interface BoardMapper {
	
	// 하나의 게시글 작성하여 추가하는 쿼리문
	public void insertBoard(BoardDTO bdto);
	
	// 전체 게시글 목록을 출력하는 쿼리문
	public List<BoardDTO> getAllBoard();
	
	// readcount 누적해야함
	public int getOneBoardUpdate(int num);
	public BoardDTO getOneBoardSelect(int num);
	
	// 검색 버튼 클릭시 검색어가 포함된 게시글 select
	// 검색 메서드 => searchType, searchKeyWord 매개변수 반드시 필요 => 공식
	public List<BoardDTO> getSearchBoard(@Param("searchType") String searchType, @Param("searchKeyWord") String searchKeyWord);
	
	// 하나의 게시글을 수정하는 메서드
	public int updateBoard(BoardDTO bdto);
	
	// 게시글 작성시 비밀번호를 입력하였기때문에 삭제시에도 비밀번호와 번호가 일치하는지 체크
	// @Param("변수") 데이터 타입 필드명 => 매개 변수가 2개 이상일 경우
	public int deleteBoard(@Param("num") int num, @Param("writerPw") String writerPw);

}
