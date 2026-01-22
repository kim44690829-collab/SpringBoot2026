package com.green;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// MemberService 클래스는 비즈니스 로직을 작성하는 클래스 이다.
// 방법 3 : @Service를 이용한 객체 생성 / 방법 2와 마찬가지로 @Autowired가 필요함 
@Service
public class MemberService {

	// MemberDAO 클래스를 MemberService클래스에서 사용하는 방법 
	// => ID(@Autowired) 를 통해 외부로부터 객체를 주입해 사용 
	@Autowired
	MemberDAO mdao;
	
	public void signUpConfirm(MemberDTO mdto) {
		System.out.println("회원가입 출력화면");
		mdao.insertMember(mdto);
	}

	public void signinConfirm(MemberDTO mdto) {
		System.out.println("로그인 출력화면");
		MemberDTO loginMember = mdao.selectMember(mdto);
		
		// Cannot invoke "com.green.MemberDTO.getId()" because "loginMember" is null
		// => null값이 들어가서 NullPointerException 오류가 났음. => 예외처리 해야함
		if(loginMember != null && (mdto.getPw().equals(loginMember.getPw()))) {
			System.out.println("id : " + loginMember.getId());
			System.out.println("pw : " + loginMember.getPw());
			System.out.println("로그인 성공");
		}else {
			System.out.println("로그인 실패");
		}
	}

	
}
