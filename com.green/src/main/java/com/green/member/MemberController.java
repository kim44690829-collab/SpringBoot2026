package com.green.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
	
	// MemberService 클래스를 DI로 의존객체화 해야함
	@Autowired
	MemberService memberservice;
	
	// 회원가입 양식 폼
	@GetMapping("/member/signup")
	public String signup() {
		System.out.println("memberController : signup() 메서드 확인");
		String nextPage = "member/signup_form";
		return nextPage;
	}
	
	// 회원가입 확인
	@PostMapping("/member/signup_confirm")
	public String signupConfirm(MemberDTO mdto, Model model) {
		System.out.println("memberController : signupConfirm() 메서드 확인");
		String nextPage = "member/signup_result";
		// 회원가입이 제대로 되었는지, 실패했는지 예외처리
		int result = memberservice.signupConfirm(mdto);
		
		// 회원가입이 성공하였을 경우 회원 전체 목록으로 보내버림
		if(result == memberservice.user_id_success) {
			return "redirect:/member/list";
		}else {
			// 회원가입 실패
			model.addAttribute("result", result);
			return nextPage;
		}
	}
	
	// 회원 전체 목록 화면
	@GetMapping("/member/list")
	public String memberList(Model model) {
		// MemberService memberAll()
		List<MemberDTO> memberlist = memberservice.memberAll();
		model.addAttribute("list", memberlist);
		
		String nextPage = "member/memberList";
		return nextPage;
	}
}
