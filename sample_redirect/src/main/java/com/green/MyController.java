package com.green;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MyController {
	
	// redirect 실습
//	@GetMapping("/old-url")
//	public String oldPage() {
//		// "new-url" 주소로 redirect 하라는 의미
//		// redirect => 새로운 주소로 이동하라는 의미
//		return "redirect:/new-url";
//	}
//	
//	@GetMapping("/new-url")
//	public String newPage() {
//		// new-page-view.html
//		return "new-page-view";
//	}
	
	// RedirectAttributes 실습
	@GetMapping("/old-url")
	public String oldPage(RedirectAttributes re) {
		// redirect 시점에서 딱 한번만 사용할 데이터를 임시로 보관함
		re.addFlashAttribute("msg", "이전 주소에서 자동으로 이동되었습니다!!");
		return "redirect:/new-url";
	}
	
	@GetMapping("/new-url")
	public String newPage() {
		// new-page-view.html
		return "new-page-view";
	}
}
