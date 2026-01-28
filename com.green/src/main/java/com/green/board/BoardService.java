package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO boardDao;
	
	// 하나의 게시글이 추가되는 메서드를 BoardDAO에 접근하여 사용
	public void addBoard(BoardDTO bdto) {
		System.out.println("3. BoardService : addBoard() 메서드 호출");
		boardDao.insertBoard(bdto);
	}
	
	// 게시글 전체의 목록을 출력하는 메서드
	public List<BoardDTO> allBoard(){
		System.out.println("3. BoardService : allBoard() 메서드 호출");
		return boardDao.getAllBoard();
	}
	
	// 하나의 게시글을 출력하는 메서드
	public BoardDTO oneBoard(int num) {
		System.out.println("3. BoardService : allBoard() 메서드 호출");
		return boardDao.getOneBoard(num);
	}
	
	// 하나의 게시글을 수정하는 메서드
	public boolean modifyBoard(BoardDTO bdto) {
		System.out.println("3. BoardService : modifyBoard() 메서드 호출");
		
		int result = boardDao.updateBoard(bdto);
		
		if(result > 0) {
			System.out.println("게시글 수정 성공");
			return true;
		}else {
			System.out.println("게시글 수정 실패");
			return false;
		}
	}
}
