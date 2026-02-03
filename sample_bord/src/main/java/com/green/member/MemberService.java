package com.green.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.green.member.mapper.MemberMapper;

@Service
public class MemberService {
	
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	// 회원가입
	public boolean signupConfirm(MemberDTO mdto) {
		System.out.println("MemberService : signupConfirm()");
		int isMember = memberMapper.isMember(mdto.getUserId());
		
		if(isMember == 0) {
			// insert 할때 pw를 암호화해서 추가
			String encodePw = passwordEncoder.encode(mdto.getUserPw());
			
			mdto.setUserPw(encodePw);
			int result = memberMapper.signupInsert(mdto);
			
			if(result > 0) {
				System.out.println("로그인 성공");
				return true;
			}else {
				System.out.println("로그인 실패");
				return false;
			}
		}else {
			System.out.println("로그인 실패");
			return false;
		}
	}
	
	// 로그인을 위한 서비스로직
	public MemberDTO oneSelectMember(MemberDTO mdto) {
		System.out.println("MemberService : oneSelectMember()");
		
		MemberDTO DBmember = memberMapper.oneSelect(mdto.getUserId());
		
		if(DBmember != null && DBmember.getUserPw() != null) {
			if(passwordEncoder.matches(mdto.getUserPw(), DBmember.getUserPw())) {
				return DBmember;
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
