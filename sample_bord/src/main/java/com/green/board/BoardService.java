package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.board.mapper.BoardMapper;

@Service
public class BoardService {
	
	@Autowired
//	BoardDAO boarddao;
	BoardMapper boardMapper;
	
	
	// insert
	public boolean writeBoard(BoardDTO bdto) {
		System.out.println("BoardService : writeBoard() 메서드 확인");
		return boardMapper.insertBoard(bdto) == 1;
	}
	
	// 전체 게시글 select
	public List<BoardDTO> allBoard(){
		System.out.println("BoardService : allBoard() 메서드 확인");
		return boardMapper.allSelect();
	}
	
	// 한 사람의 게시글 select
	public BoardDTO oneBoard(int id) {
		System.out.println("BoardService : oneBoard() 메서드 확인");
		return boardMapper.oneSelect(id);
	}
	
	// 한 사람의 게시글 삭제
	public boolean onedel(int id) {
		System.out.println("BoardService : onedel() 메서드 확인");
		return boardMapper.oneDelete(id) == 1;
	}
	
	// 한 사람의 게시글 수정
	public boolean oneMod(BoardDTO bdto) {
		System.out.println("BoardService : oneMod() 메서드 확인");
		return boardMapper.modBoard(bdto) == 1;
	}
	
	// 게시글 검색
	public List<BoardDTO> searchlist(String searchType, String searchKeyword){
		System.out.println("BoardService : searchlist() 메서드 확인");
		return boardMapper.searchBoard(searchType, searchKeyword);
	}
	
	// 전체 개수 출력 메서드
	public int allCount() {
		System.out.println("BoardService : allCount() 메서드 확인");
		return boardMapper.getAllCount();
	}
	
	public List<BoardDTO> pageList(int startRow, int pageSize){
		System.out.println("BoardService : pageList() 메서드 확인");
		return boardMapper.getPageList(startRow, pageSize);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
