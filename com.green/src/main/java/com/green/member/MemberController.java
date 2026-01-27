package com.green.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		System.out.println("memberController : memberList() 메서드 확인");
		// MemberService memberAll()
		List<MemberDTO> memberlist = memberservice.memberAll();
		model.addAttribute("list", memberlist);
		
		String nextPage = "member/memberList";
		return nextPage;
	}
	
	// --------------------------------- 2026-01-27 controller 로직 작성--------------------------------------------------
	// 한 개인의 정보를 상세보기하는 핸들러
	@GetMapping("/member/memberInfo")
	public String memberInfo(Model model, MemberDTO mdto) {
		System.out.println("memberController : memberInfo() 메서드 확인 / 호출한 아이디 : " + mdto.getId());
		MemberDTO oneMemberInfo = memberservice.oneSelect(mdto.getId());
		
		model.addAttribute("oneList",oneMemberInfo);
		String nextPage = "member/memberInfo";
		return nextPage;
	}
	
	// 개인 정보 수정 핸들러
	@GetMapping("/member/modify")
	public String modifyForm(MemberDTO mdto, Model model) {
		System.out.println("memberController : modifyForm() 메서드 확인 / 호출한 아이디 : " + mdto.getId());
		
		// 개인 수정 화면 그릴때 필요한 정보 : 한사람의 데이터 => oneSelect() 사용
		MemberDTO oneModify = memberservice.oneSelect(mdto.getId());
		
		model.addAttribute("member", oneModify);
		String nextPage = "member/member_modify";
		return nextPage;
	}
	
	// 개인정보 수정을 처리하는 핸들러
	// 비밀번호가 일치하는지의 비교에 관련한 핸들러
	// redirect 사용
	// modifyMember() 사용
	
	@PostMapping("/member/modify")
	public String modifySubmit(MemberDTO mdto, RedirectAttributes rea) {
		System.out.println("memberController : modifySubmit() 메서드 확인");
		boolean result = memberservice.modifyMember(mdto);
		// 지금 result => true or false
		
		if(result == true) {
			// 업데이트 성공
			// RedirectAttributes => 한번만 데이터를 넘길 수 있다.
			rea.addFlashAttribute("msg", "회원 정보가 수정되었습니다.");
			return "redirect:/member/list";
		}else {
			// 비밀번호가 틀렸을 때
			// rea.addAttribute("msg", "비밀번호가 틀렸습니다.");
			rea.addFlashAttribute("msg", "비밀번호가 틀렸습니다.");
			// 비밀번호가 틀리면 위의 주소로 다시 이동
			return "redirect:/member/modify?id=" + mdto.getId();
		}
	}
	
	// 개인 한 사람의 정보를 삭제하는 핸들러
	@GetMapping("/member/delete")
	public String deleteMember(@RequestParam("id") String id, RedirectAttributes rea) {
		System.out.println("memberController : deleteMember() 메서드 확인");
		boolean result = memberservice.oneDelete(id);
		// 삭제가 된 경우 => true, 삭제가 안된경우 => false
		if(result) {
			// id에 해당하는 정보가 삭제가 된 경우 
			rea.addFlashAttribute("msg", "회원이 삭제되었습니다.");
			return "redirect:/member/list";
		}else {
			// 삭제가 진행되지 않은경우
			rea.addFlashAttribute("msg", "삭제 실패");
			return "redirect:/member/memberInfo?id=" + id;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
