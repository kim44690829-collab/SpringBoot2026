package com.green.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberservice;
	
	// 회원가입 메서드
	@GetMapping("/member/signup")
	public String signupForm() {
		System.out.println("MemberController : signup()");
		String nextPage = "member/signupForm";
		return nextPage;
	}
	
	@PostMapping("/member/signupPro")
	public String signupPro(MemberDTO mdto, Model model) {
		System.out.println("MemberController : signupPro()");
		
		boolean result = memberservice.signupConfirm(mdto);
		
		model.addAttribute("result", result);

		String nextPage = "member/signupResult";
		return nextPage;
	}
	
	@GetMapping("/member/login")
	public String loginForm() {
		System.out.println("MemberController : login()");
		String nextPage = "member/loginForm";
		return nextPage;
	}
	
	@PostMapping("/member/loginPro")
	public String loginPro(MemberDTO mdto, HttpSession session) {
		System.out.println("MemberController : loginPro()");
		
		MemberDTO members = memberservice.oneSelectMember(mdto);
		
		if(members != null) {
			session.setAttribute("members", members);
			return "redirect:/";
		}else {
			return "redirect:/member/login";
		}
	}
	
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		System.out.println("MemberController : logout()");
		session.invalidate();
		return "redirect:/";
	}
	
}
