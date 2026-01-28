package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardservice;

	// 1. 게시글 작성 form 화면으로 이동하는 핸들러
	@GetMapping("/board/write")
	public String boardWriteForm(){
		System.out.println("1. BoardController : boardWriteForm() 메서드 호출");
		String nextPage = "board/boardWrite_form";
		return nextPage;
	}
	
	// 2. form에서 입력한 데이터를 DB에 영구 저장 => insert하는 controller 가 필요
	@PostMapping("/board/writePro")
	public String boardWritePro(BoardDTO bdto) {
		System.out.println("1. BoardController : boardWritePro() 메서드 호출");
		
		// 서비스의 addBoard()메서드 호출해서 DB에 저장
		boardservice.addBoard(bdto);
		
		// 저장 후에는 게시판 목록으로 페이지 이동(redirect)
		return "redirect:/board/list";
	}
	
	// 3. DB에서 전체 게시글 목록 select로 검색해서 추출 -> 모델(model) 객체에 담는다. => 전체 목록 화면으로 이동 후 뿌린다.
	@GetMapping("/board/list")
	public String boardList(Model model) {
		System.out.println("1. BoardController : boardList() 메서드 호출");
		List<BoardDTO> boardlist = boardservice.allBoard();
		model.addAttribute("list", boardlist);
		
		String nextPage = "board/boardList";
		return nextPage;
	}
	
	// 4. 하나의 게시글 상세정보 확인 핸들러
	// num을 받아 해당 게시글을 DB에서 조회하고 상세정보를 저장하는 컨트롤러 
	@GetMapping("/board/boardInfo")
	public String boardInfo(@RequestParam("num") int num, Model model) {
		System.out.println("1. BoardController : boardInfo() 메서드 호출");
		
		BoardDTO oneboardInfo = boardservice.oneBoard(num);
		model.addAttribute("oneboard", oneboardInfo);
		
		String nextPage = "board/boardInfo";
		return nextPage;
	}
	
	// 5. 게시글 수정 form으로 이동하는 controller
	@GetMapping("/board/update")
	public String boardUpdateForm(@RequestParam("num") int num, Model model) {
		System.out.println("1. BoardController : boardUpdateForm() 메서드 호출");
		
		// 기존에 하나의 게시글을 불러오는 쿼리를 이용하여 게시글 하나를 불러와서 수정
		BoardDTO oneboardInfo = boardservice.oneBoard(num);
		model.addAttribute("oneboard", oneboardInfo);
		
		String nextPage = "board/boardUpdate_form";
		return nextPage;
	}
	
	// 6. 하나의 게시글 수정을 처리하는 controller
	@PostMapping("/board/updatePro")
	public String boardUpdatePro(BoardDTO bdto, Model model) {
		System.out.println("1. BoardController : boardUpdatePro() 메서드 호출");
		
		boolean result = boardservice.modifyBoard(bdto);
		// 수정 완료 => true, 수정 미완료 => false
		if(result) {
			// 수정 성공시 리스트
			return "redirect:/board/list";
		}else {
			// 수정 실패시 현재 수정하고있는 form => num을 가진 상태여야함
			return "redirect:/board/update?num=" + bdto.getNum();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
