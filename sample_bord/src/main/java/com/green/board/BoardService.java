package com.green.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO boarddao;
	
	public boolean writeBoard(BoardDTO bdto) {
		System.out.println("BoardService : writeBoard() 메서드 확인");
		return boarddao.insertBoard(bdto) == 1;
	}
	
}
