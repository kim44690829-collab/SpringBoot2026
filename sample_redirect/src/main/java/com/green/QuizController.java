package com.green;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class QuizController {
	
	@GetMapping("/quiz")
	public String quizPage() {
		return "quiz-view";
	}
	
	@PostMapping("/check")
	public String checkPage(
			RedirectAttributes re, 
			@RequestParam("userPw") String pw
			) {
		if(pw.equals("1234")) {
			re.addFlashAttribute("msg", "리다이렉트를 통해 안전하게 메인 페이지에 도착하셨습니다.");
			return "redirect:/main";
		}else {
			re.addFlashAttribute("msg", "비밀번호가 틀렸습니다. 다시 시도하세요!");
			return "redirect:/quiz";
		}
	}
	
	@GetMapping("/main")
	public String mainPage() {
		return "main-view";
	}
	
	
	
}
