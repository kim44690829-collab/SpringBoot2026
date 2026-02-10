package replyBoard.service;

import java.util.List;

import replyBoard.dto.ReplyBoardDTO;

public interface ReplyBoardService {

	// 게시글 작성하여 추가하는 메서드
	public void insertReplyBoard(ReplyBoardDTO rdto);
	
	// 게시글 전체 목록 검색 메서드
	public List<ReplyBoardDTO> getAllReplyBoard();
	
	// 하나의 게시글을 검색하는 메서드
	public ReplyBoardDTO getOneBoard(int num);
	
	// 답글 작성하여 추가하기
	public void reWriteInsert(ReplyBoardDTO rdto);
	
	// 답글 작성시 부모글의 re_level보다 큰 값들을 모두 1씩 증가시키는 메서드
	// ref : 1, re_step : 1, re_level : 1 => 원글
	// ref : 1, re_step : 2, re_level : 2 => 답글
	public void reSqUpdate(ReplyBoardDTO rdto);
	
	// 답글 추가시 reSqUpdate() 메서드가 먼저 실행되도록 묶음으로 만든 메서드
	// reWriteInsert() + reSqUpdate() 를 합쳐서 실행하는 메서드
	// 답글이 추가되기 전에 기존의 ref, re_step, re_level의 값이 변경되야하는 부분이 필요하기 때문에
	// reSqUpdate()를 먼저 실행해서 기존의 값을 변경, reWriteInsert() 이후 삽입
	public void replyProcess(ReplyBoardDTO rdto);
	
}
