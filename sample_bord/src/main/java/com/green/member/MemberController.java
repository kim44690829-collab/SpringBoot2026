package com.green.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	
	// 회원가입 메서드
	@GetMapping("/member/signup")
	public String signup() {
		String nextPage = "member/signupForm";
		return nextPage;
	}
	
	
}
