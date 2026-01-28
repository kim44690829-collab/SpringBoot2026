package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardservice;
	
	// 게시판 목록
	@GetMapping("/board/list")
	public String boardList(BoardDTO bdto, Model model) {
		System.out.println("BoardController : boardList() 메서드 확인");
		boardservice.writeBoard(bdto);
		List<BoardDTO> boardlist = boardservice.allBoard();
		model.addAttribute("list", boardlist);
		
		String nextPage = "board/boardMain";
		return nextPage;
	}
	
	// 게시글 작성
	@GetMapping("/board/write")
	public String boardWrite() {
		System.out.println("BoardController : boardWrite() 메서드 확인");
		String nextPage = "board/boardWrite";
		return nextPage;
	}

	// 하나의 게시판 클릭
	@GetMapping("/board/oneboard")
	public String oneboard(BoardDTO bdto, Model model) {
		System.out.println("BoardController : oneboard() 메서드 확인");
		BoardDTO oneBoardSelect = boardservice.oneBoard(bdto.getId());
		model.addAttribute("boardOne", oneBoardSelect);
		String nextPage = "board/oneBoard";
		return nextPage;
	}
	
	// 하나의 게시글 삭제
	@GetMapping("/board/delete")
	public String boardDel(@RequestParam("id") int id, RedirectAttributes rea) {
		System.out.println("BoardController : boardDel() 메서드 확인");
		boolean result = boardservice.onedel(id);
		if(result) {
			rea.addFlashAttribute("msg", "게시글이 삭제되었습니다.");
			return "redirect:/board/list";
		}else {
			rea.addFlashAttribute("msg", "게시글 삭제에 실패했습니다.");
			return "redirect:/board/oneboard?id=" + id;
		}
	}
	
	@GetMapping("/board/modify")
	public String modScreen(BoardDTO bdto, Model model) {
		System.out.println("BoardController : modScreen() 메서드 확인");
		
		BoardDTO oneBoard = boardservice.oneBoard(bdto.getId());
		model.addAttribute("oneBoard", oneBoard);
		
		String nextPage = "board/boardMod";
		return nextPage;
	}
	
	// 게시글 수정
	@PostMapping("/board/modify")
	public String oneBoardMod(BoardDTO bdto, RedirectAttributes rea) {
		System.out.println("BoardController : oneBoardMod() 메서드 확인");
		
		boolean result = boardservice.oneMod(bdto);
		
		if(result) {
			rea.addFlashAttribute("msg", "게시글이 수정되었습니다.");
			return "redirect:/board/oneboard?id=" + bdto.getId();
		}else {
			rea.addFlashAttribute("msg", "게시글 수정에 실패했습니다.");
			return "redirect:/board/modify?id=" + bdto.getId();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
