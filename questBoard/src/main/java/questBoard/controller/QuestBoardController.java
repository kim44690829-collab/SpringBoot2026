package questBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import questBoard.dto.QuestBoardDTO;
import questBoard.service.QuestBoardService;

@Controller
public class QuestBoardController {
	
	@Autowired
	QuestBoardService questBoardService;
	
	@GetMapping("/board/list")
	public String questBoardList(Model model) {
		System.out.println("QuestBoardController : questBoardList() 메서드 확인");
		List<QuestBoardDTO> boardList = questBoardService.boardAllSelect();
		model.addAttribute("list", boardList);
		return "/questBoard/questboardList";
	}
	
	@GetMapping("/board/write")
	public String questBoardWriteForm() {
		System.out.println("QuestBoardController : questBoardWriteForm() 메서드 확인");
		return "/questBoard/questboardWrite_Form";
	}
	
	@PostMapping("/board/writePro")
	public String writePro(QuestBoardDTO qdto) {
		System.out.println("QuestBoardController : writePro() 메서드 확인");
		questBoardService.questBoardInsert(qdto);
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/detail")
	public String boardDetail(@RequestParam("num") int num, Model model) {
		System.out.println("QuestBoardController : boardDetail() 메서드 확인");
		
		QuestBoardDTO qdto = questBoardService.boardOneSelect(num);
		
		model.addAttribute("one", qdto);
		
		return "/questBoard/questboardDetail";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
