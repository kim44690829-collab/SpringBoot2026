package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO boarddao;
	
	// insert
	public void writeBoard(BoardDTO bdto) {
		System.out.println("BoardService : writeBoard() 메서드 확인");
		boarddao.insertBoard(bdto);
	}
	
	// 전체 게시글 select
	public List<BoardDTO> allBoard(){
		System.out.println("BoardService : allBoard() 메서드 확인");
		return boarddao.allSelect();
	}
	
	// 한 사람의 게시글 select
	public BoardDTO oneBoard(int id) {
		System.out.println("BoardService : oneBoard() 메서드 확인");
		return boarddao.oneSelect(id);
	}
	
	// 한 사람의 게시글 삭제
	public boolean onedel(int id) {
		System.out.println("BoardService : onedel() 메서드 확인");
		return boarddao.oneDelete(id) == 1;
	}
	
	// 한 사람의 게시글 수정
	public boolean oneMod(BoardDTO bdto) {
		System.out.println("BoardService : oneMod() 메서드 확인");
		System.out.println("오잉 ? " + boarddao.modBoard(bdto));
		return boarddao.modBoard(bdto) == 1;
	}
}
