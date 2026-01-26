package com.green.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// @Service 역할 
// controller -> service 한테 DAO 메서드 찾았어? 라고 물어봄
// service -> DAO 한테 DAO 메서드 있어? 라고 물어봄
// DAO -> DB 있는지 찾아보고 있으면 찾아옴
// DB 에서 id, pw값을 들고 -> DAO -> service의 메서드로 보냄
// service -> controller에게 id, pw 보냄 
@Service
public class MemberService {
	
	// id 중복체크, 성공, 실패 상수변수 정의
	// 회원가입의 중복을 확인하는 상수변수
	public final static int user_id_already_exit = 0;
	// 회원가입 성공여부를 확인하는 상수
	public final static int user_id_success = 1;
	// 회원가입 실패 확인하는 상수
	public final static int user_id_fail = -1;
	
	// MemberDAO DI
	@Autowired
	MemberDAO memberdao;
	
	// 회원 전체 목록 출력하는 메서드
	public List<MemberDTO> memberAll(){
		return memberdao.allSelectMember();
	}
	
	// 회원가입이 제대로 되었는지, 실패했는지 예외처리
	public int signupConfirm(MemberDTO mdto) {
		System.out.println("MemberService : signupConfirm() 매서드 확인");
		
		// 회원가입 중복체크
		boolean isMember = memberdao.isMember(mdto.getId());
		
		// 회원가입 중복체크 통과
		if(isMember == false) {
			// 중복된 아이디가 존재하지 않을 경우 DB에 추가/삽입되어야한다.
			int result = memberdao.insertMember(mdto); 
			if(result > 0) {
				return user_id_success; // 가입 성공 -> result = 1;
			}else {
				return user_id_fail; // 가입 실패 -> result = -1;
			}
		}else {
			// 중복된 아이디가 존재할 때
			return user_id_already_exit; // 중복 아이디 -> result = 0;
		}
	}
	
}
