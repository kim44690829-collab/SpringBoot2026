package replyBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import replyBoard.dto.ReplyBoardDTO;
import replyBoard.mapper.ReplyBoardMapper;

@Service
public class ReplyBoardServiceImpl implements ReplyBoardService{

	@Autowired
	ReplyBoardMapper replyBoardMapper;
	
	@Override
	public void insertReplyBoard(ReplyBoardDTO rdto) {
		System.out.println("ReplyBoardServiceImpl : insertReplyBoard() 메서드 확인");
		replyBoardMapper.insertReplyBoard(rdto);
	}

	@Override
	public List<ReplyBoardDTO> getAllReplyBoard() {
		System.out.println("ReplyBoardServiceImpl : getAllReplyBoard() 메서드 확인");
		return replyBoardMapper.getAllReplyBoard();
	}

	@Override
	public ReplyBoardDTO getOneBoard(int num) {
		System.out.println("ReplyBoardServiceImpl : getOneBoard() 메서드 확인");
		return replyBoardMapper.getOneBoard(num);
	}

	@Override
	public void reWriteInsert(ReplyBoardDTO rdto) {
		System.out.println("ReplyBoardServiceImpl : reWriteInsert() 메서드 확인");
		replyBoardMapper.reWriteInsert(rdto);
	}

	@Override
	public void reSqUpdate(ReplyBoardDTO rdto) {
		System.out.println("ReplyBoardServiceImpl : reSqUpdate() 메서드 확인");
		replyBoardMapper.reSqUpdate(rdto);
	}

	@Override
	public void replyProcess(ReplyBoardDTO rdto) {
		System.out.println("ReplyBoardServiceImpl : replyProcess() 메서드 확인");
		// 반드시 update() 메서드를 먼저 실행해야한다.
		replyBoardMapper.reSqUpdate(rdto);
		// 답글 insert() 메서드
		replyBoardMapper.reWriteInsert(rdto);
	}

}
