package com.green.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardservice;
	
	@GetMapping("/board/list")
	public String boardList() {
		System.out.println("BoardController : boardList() 메서드 확인");
		String nextPage = "board/boardMain";
		return nextPage;
	}
	
	@GetMapping("/board/write")
	public String boardWrite() {
		System.out.println("BoardController : boardWrite() 메서드 확인");
		String nextPage = "board/boardWrite";
		return nextPage;
	}

	@GetMapping("/board/write")
	public String writeConfirm(BoardDTO bdto, RedirectAttributes rea) {
		System.out.println("BoardController : writeConfirm() 메서드 확인");
		boolean result = boardservice.writeBoard(bdto);
		if(result) {
			return "redirect:/board/list";
		}else {
			rea.addFlashAttribute("msg", "게시판 작성 실패");
			return "redirect:/board/write";
		}
	}
}
