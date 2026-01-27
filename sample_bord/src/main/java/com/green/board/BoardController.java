package com.green.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardservice;
	
	@GetMapping("/board/list")
	public String boardList(BoardDTO bdto, Model model) {
		System.out.println("BoardController : boardList() 메서드 확인");
		
		boardservice.writeBoard(bdto);
		
		model.addAttribute("id",bdto.getId());
		model.addAttribute("title",bdto.getTitle());
		model.addAttribute("writer",bdto.getWriter());
		model.addAttribute("createdAt",bdto.getCreatedAt());
		
		String nextPage = "board/boardMain";
		return nextPage;
	}
	
	@GetMapping("/board/write")
	public String boardWrite() {
		System.out.println("BoardController : boardWrite() 메서드 확인");
		String nextPage = "board/boardWrite";
		return nextPage;
	}

}
