package com.green.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.book.dto.MemberDTO;
import com.green.book.service.MemberService;

@Controller
public class MemberController {
	
		@Autowired
		MemberService memberS;
		
	
		// 회원가입 폼
		@GetMapping("/signup")
		public String signup() {
			return "signup";
		}
		
		// 회원가입 완료 화면
		@PostMapping("/signupResult")
		public String signupResult(MemberDTO mdto, Model model) {
			memberS.signupConfirm(mdto);
			model.addAttribute("id", mdto.getUserId());
			model.addAttribute("pw", mdto.getUserPw());
			System.out.println(mdto.getUserId() + " --- " + mdto.getUserPw());
			return "signupResult";
		}
		
		// 로그인 폼
		@GetMapping("/login")
		public String login() {
			return "login";
		}
		
		// 로그인 완료 폼
		@PostMapping("/loginResult")
		public String loginResult(MemberDTO mdto) {
			memberS.loginConfirm(mdto);
			return "loginResult";
		}
}
