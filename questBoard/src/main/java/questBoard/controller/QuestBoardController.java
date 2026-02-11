package questBoard.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	
//	@PostMapping("/board/writePro")
//	public String writePro(QuestBoardDTO qdto) {
//		System.out.println("QuestBoardController : writePro() 메서드 확인");
//		questBoardService.questBoardInsert(qdto);
//		return "redirect:/board/list";
//	}
	
	@PostMapping("/board/writePro")
	public String writePro(QuestBoardDTO qdto, 
			@RequestParam("file1") MultipartFile upload1,
			@RequestParam("file2") MultipartFile upload2
			) throws IllegalStateException, IOException {
		System.out.println("QuestBoardController : writePro() 메서드 확인");
		
		// 파일을 저장할 하드디스크 위치 - WebConfig에서 설정한 경로와 일치해야함
		String savePath = "c:/upload/";
		
		// 폴더가 없으면 자동 생성
		File saveDir = new File(savePath);
		if(!saveDir.exists()) {
			saveDir.mkdirs();
		}
		
		if(!upload1.isEmpty()) {
			// 사용자가 올린 파일명을 가져옴
			String saveName1 = upload1.getOriginalFilename();
			
			File file1 = new File(savePath + saveName1);
			
			upload1.transferTo(file1);
			
			qdto.setUpload1(saveName1);
		}
		
		if(!upload2.isEmpty()) {
			// 사용자가 올린 파일명을 가져옴
			String saveName2 = upload2.getOriginalFilename();
			
			File file2 = new File(savePath + saveName2);
			
			upload2.transferTo(file2);
			
			qdto.setUpload2(saveName2);
		}
		
		questBoardService.questBoardInsert(qdto);
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/detail")
	public String boardDetail(@RequestParam("num") int num, @RequestParam("ref") int ref, @RequestParam("is_answered") int is_answered, Model model) {
		System.out.println("QuestBoardController : boardDetail() 메서드 확인");
		
		QuestBoardDTO qdto = questBoardService.boardOneSelect(num);
		model.addAttribute("one", qdto);
		
		
		
		return "/questBoard/questboardDetail";
	}
	
	@GetMapping("/board/rewrite")
	public String rewriteForm(
			Model model,
			@RequestParam("num") int num,
			@RequestParam("ref") int ref,
			@RequestParam("re_step") int re_step
			) {
		System.out.println("QuestBoardController : rewriteForm() 메서드 확인");
		
		model.addAttribute("num", num);
		model.addAttribute("ref", ref);
		model.addAttribute("re_step", re_step);
		
		return "/questBoard/questboardRewrite_Form";
	}
	
	@PostMapping("/board/reWritePro")
	public String rewritePro(QuestBoardDTO qdto) {
		System.out.println("QuestBoardController : rewriteForm() 메서드 확인");
		questBoardService.ansProcess(qdto);
		return "redirect:/board/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
