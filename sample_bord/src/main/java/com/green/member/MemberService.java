package com.green.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.member.mapper.MemberMapper;

@Service
public class MemberService {
	
	@Autowired
	MemberMapper memberMapper;
	
	// 회원가입
	public boolean signupConfirm(MemberDTO mdto) {
		System.out.println("MemberService : signupConfirm()");
		int result = memberMapper.signupInsert(mdto);
		boolean isMember = memberMapper.isMember(mdto.getUserId());
		if(isMember != true) {
			if(result > 0) {
				
				
			}else {
				
			}
		}
		
		
		
		return memberMapper.signupInsert(mdto) == 1;
	}
}
