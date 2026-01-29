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
	
	// 게시글 검색하는 메서드
	public List<BoardDTO> searchBoard(String searchType, String searchKeyWord){
		System.out.println("3. BoardService : searchBoard() 메서드 호출");
		System.out.println("3. searchType : " + searchType );
		System.out.println("3. searchKeyWord : " + searchKeyWord );
		
		return boardDao.getSearchBoard(searchType, searchKeyWord);
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
	
	// 게시글 하나를 삭제하는 메서드
	public boolean removeBoard(int num, String writerPw) {
		System.out.println("3. BoardService : removeBoard() 메서드 호출");
		// DAO에서 받아오는 deleteBoard()는 삭제되었으면 1, 아니면 0
		int result = boardDao.deleteBoard(num, writerPw);
		if(result > 0) {
			System.out.println("게시글 삭제 성공");
			return true;
		}else {
			System.out.println("게시글 삭제 실패");
			return false;
		}
	}
}
