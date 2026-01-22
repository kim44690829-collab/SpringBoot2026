package com.green.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.book.dao.DataDAO;
import com.green.book.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	DataDAO dDao;
	
	public void signupConfirm(MemberDTO mdto) {
		System.out.println("회원가입 완료");
		dDao.insertMember(mdto);
	}
	
	public void loginConfirm(MemberDTO mdto) {
		System.out.println("로그인 완료");
		
		MemberDTO loginMember = dDao.selectMember(mdto);
		
		if(loginMember != null && mdto.getUserPw().equals(loginMember.getUserPw())) {
			System.out.println("id : " + loginMember.getUserId());
			System.out.println("pw : " + loginMember.getUserPw());
			System.out.println("로그인 성공");
		}else {
			System.out.println("로그인 실패");
		}
		
	}
}
