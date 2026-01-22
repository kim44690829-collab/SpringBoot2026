package com.green;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	
	// DI(의존성 객체 주입)
	// MemberContontroller가 직접 MemberService를 생성하지 않고 스프링 컨테이너가 만든 MemberService를 주입시켜라
	@Autowired
	MemberService memberService;
	
	// 아래 작성한 메서드 => 핸들러 메서드
	// 회원가입 양식 (mapping = 연결)
	@GetMapping("/member/signup") // http://localhost:8090/member/signup
	public String signupForm() {
		// 아래 프린트문은 logo역할
		System.out.println("signUpForm()");
		return "signUpForm"; // 응답에 사용하는 html 파일 이름
	}
	
	// 로그인 양식 (mapping = 연결)
	@GetMapping("/member/signin")
	public String signinForm() {
		System.out.println("signinForm()");
		// src/main/resources/signinForm.html과 연동 => URL주소 : @GetMapping("/member/signin") 의 /member/signin 이다. 
		return "signinForm";
	}
	
	// 회원가입한 데이터가 signUpResult 페이지로 전달되는 메서드 (mapping = 연결)
	// 숨겨서 가는 @PostMapping() 사용
	// 가입한 자료를 매개변수로 넘겨줘야한다. => @RequestParam 사용
	@PostMapping("/member/signUp_confirm")
	public String signupconfirm(MemberDTO mdto, Model model) {
		System.out.println("signupconfirm()");
		
		// MemberService 비즈니스 로직을 담당하는 클래스 인스턴스화
		// 방법 1 : new 라는 키워드를 이용하여 객체 생성
		// MemberService memberService = new MemberService();
		// memberService.signUpConfirm(mdto);
		
		// 방법 2 : (@Configuration, @Bean) => AppConfig 클래스, @Autowired => MemberController 클래스 상단 를 이용해서 객체 생성
		memberService.signUpConfirm(mdto);
		
		// 현재 가입한 시간을 출력하는 로직 => 2026-01-21 11:20:10
		Date now = new Date();
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		
		
		// model로 담기
		model.addAttribute("now", time.format(now));
		model.addAttribute("new_id", mdto.getId());
		model.addAttribute("new_pw", mdto.getPw());
		model.addAttribute("new_email", mdto.getEmail());
		
		return "signUpResult";
	}
	
	// ModelAndView 클래스는 model과 View를 하나로 합쳐서 클라이언트에 전달한다.
	// MemberDTO를 데이터 타입으로 매개변수 지정
	@PostMapping("/member/signin_confirm")
	public ModelAndView signinconfirm(MemberDTO mdto) {
		System.out.println("signinconfirm()");
		
		// MemberService 비즈니스 로직을 담당하는 클래스 인스턴스화
		// 방법 1 : new 라는 키워드를 이용하여 객체 생성
		// MemberService memberService = new MemberService();
		// memberService.signinConfirm(mdto);
		
		// 방법 2 : (@Configuration, @Bean) => AppConfig 클래스, @Autowired => MemberController 클래스 상단 를 이용해서 객체 생성
		memberService.signinConfirm(mdto);
		
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("id", mdto.getId());
		modelview.addObject("pw", mdto.getPw());
		// view 페이지 signinResult.html은 어떻게 추가하나
		modelview.setViewName("signinResult");
		
		return modelview;
	}
	
}
