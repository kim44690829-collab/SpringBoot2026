package com.green.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.board.mapper.BoardMapper;

@Service
public class BoardService {
	
//	@Autowired
//	BoardDAO boardDao;
	
	@Autowired
	BoardMapper boardMapper;
	
	// 하나의 게시글이 추가되는 메서드를 BoardDAO에 접근하여 사용
	public void addBoard(BoardDTO bdto) {
		System.out.println("3. BoardService : addBoard() 메서드 호출");
		boardMapper.insertBoard(bdto);
	}
	
	// 게시글 전체의 목록을 출력하는 메서드
	public List<BoardDTO> allBoard(){
		System.out.println("3. BoardService : allBoard() 메서드 호출");
		return boardMapper.getAllBoard();
	}
	
	// 하나의 게시글을 출력하는 메서드
	public boolean oneBoardChange(int num) {
		System.out.println("3. BoardService : allBoard() 메서드 호출");
		return boardMapper.getOneBoardUpdate(num) == 1;
	}
	
	public BoardDTO oneBoardSearch(int num) {
		System.out.println("3. BoardService : allBoard() 메서드 호출");
		return boardMapper.getOneBoardSelect(num);
	}
	
//	public BoardDTO oneBoard(int num) {
//		// 조회수 증가 메서드
//		boardMapper.getOneBoardUpdate(num);
//		// return
//		return boardMapper.getOneBoardSelect(num);
//	}
	
	// 게시글 검색하는 메서드
	public List<BoardDTO> searchBoard(String searchType, String searchKeyWord){
		System.out.println("3. BoardService : searchBoard() 메서드 호출");
		System.out.println("3. searchType : " + searchType );
		System.out.println("3. searchKeyWord : " + searchKeyWord );
		
		return boardMapper.getSearchBoard(searchType, searchKeyWord);
	}
	
	// 하나의 게시글을 수정하는 메서드
	public boolean modifyBoard(BoardDTO bdto) {
		System.out.println("3. BoardService : modifyBoard() 메서드 호출");
		
		int result = boardMapper.updateBoard(bdto);
		
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
		int result = boardMapper.deleteBoard(num, writerPw);
		if(result > 0) {
			System.out.println("게시글 삭제 성공");
			return true;
		}else {
			System.out.println("게시글 삭제 실패");
			return false;
		}
	}
	
	// 전체 게시글의 개수를 검색하는 메서드
	public int getAllCount() {
		System.out.println("3. BoardService : getAllCount() 메서드 호출");
		return boardMapper.getAllCount();
	}
	
	// 전체 게시글의 시작(startRow), 몇개의 행인지 (pageSize) 알아내는 메서드
	public List<BoardDTO> getPageList(int startRow, int pageSize){
		System.out.println("3. BoardService : getPageList() 메서드 호출");
		return boardMapper.getPageList(startRow, pageSize);
	}
	
	// 검색했을때 게시글 수
	public int getSearchCount(String searchType, String searchKeyWord) {
		System.out.println("3. BoardService : getSearchCount() 메서드 호출");
		
		return boardMapper.getSearchCount(searchType, searchKeyWord);
	}
	
	// 검색했을때 게시글 출력문
	public List<BoardDTO> getSearchPageList(String searchType, String searchKeyWord, int startRow, int pageSize){
		System.out.println("3. BoardService : getSearchPageList() 메서드 호출");
		return boardMapper.getSearchPageList(searchType, searchKeyWord, startRow, pageSize);
	}
	
	// 마이페이지 회원별 게시글 출력문
	public List<BoardDTO> getMyBoardList(String loginId, int startRow, int pageSize){
		System.out.println("3. BoardService : getMyBoardList() 메서드 호출");
		return boardMapper.getMyBoardList(loginId, startRow, pageSize);
	}
	// 마이페이지 회원별 게시글 수
	public int getMyBoardCount(String loginId) {
		System.out.println("3. BoardService : getMyBoardCount() 메서드 호출");
		return boardMapper.getMyBoardCount(loginId);
	}
}
