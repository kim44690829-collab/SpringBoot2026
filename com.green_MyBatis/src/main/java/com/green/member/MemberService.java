package com.green.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.green.member.mapper.MemberMapper;

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
//	@Autowired
//	MemberDAO memberdao;
	
	// 위의 memberdao 대신 memberMapper로 변경
	@Autowired
	MemberMapper memberMapper;
	
	// PasswordEncoder 객체도 DI (의존 객체)를 정의한다.
	@Autowired
	PasswordEncoder passwordEncoder;
	
	// 회원 전체 목록 출력하는 메서드
	public List<MemberDTO> memberAll(){
		System.out.println("MemberService : memberAll() 매서드 확인");
		return memberMapper.allSelectMember();
	}
	
	// 회원가입이 제대로 되었는지, 실패했는지 예외처리
	public int signupConfirm(MemberDTO mdto) {
		System.out.println("MemberService : signupConfirm() 매서드 확인");
		
		// 회원가입 중복체크
		boolean isMember = memberMapper.isMember(mdto.getId());
		
		// 회원가입 중복체크 통과
		if(isMember == false) {
			// ------------2026-01-29 암호화 추가
			// DB에 회원정보가 추가되는 부분 => 암호화가 이루어져야한다.
			// 문자인 pw를 암호화된 비밀번호로 변환해주는 코드
			// passwordEncoder.encode(암호화하고싶은 필드명 입력);
			// Encode : 암호화 / 인간언어 -> 기계어
			// Decode : 복호화 / 기계어 -> 인간언어
			String encodepw =  passwordEncoder.encode(mdto.getPw());
			
			// 암호화된 encodepw를 mdto에 넣어서 수정해야한다.
			mdto.setPw(encodepw);
			
			// 중복된 아이디가 존재하지 않을 경우 DB에 추가/삽입되어야한다.
			int result = memberMapper.insertMember(mdto); 
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
	
	// --------------------------------- 2026-01-27 서비스 로직 작성--------------------------------------------------
	// 한 사람의 정보를 검색하는 서비스로직
	public MemberDTO oneSelect(String id) {
		System.out.println("MemberService : oneSelect() 매서드 확인");
		return memberMapper.oneSelectMember(id);
	}
	
	// 암호화된 DB를 복호화하여 로그인하는 메서드
	public MemberDTO loginConfirm(MemberDTO mdto) {
		System.out.println("MemberService : loginConfirm() 매서드 확인");
		
		// 1. DB에서 해당 정보의 id 가져오기
		MemberDTO dbMember = memberMapper.oneSelectMember(mdto.getId());
		
		// 2. DB에서 꺼내온 id의 비밀번호와 입력한 비밀번호가 일치한지 비교
		// 2번을 하기위해 암호화된 비밀번호를 복호화 => PasswordEncoder.matches(사용자가 입력한 값, DB에 저장된 암호문)
		if(dbMember != null && dbMember.getPw() != null) {
			// 복호화 후 비교
			if(passwordEncoder.matches(mdto.getPw(), dbMember.getPw())) {
				// 로그인 성공
				return dbMember;
			}
		}
		// 로그인 실패
		return null;
	}
	
	// 한 사람의 패스워드만 출력하는 메서드
	public String onePass(String id) {
		System.out.println("MemberService : onePass() 매서드 확인");
		// void가 아닌 이상 데이터 타입이 존재한다는 것은 return이 필요함
		return memberMapper.getPass(id);
	}
	
	// 개인 한 사람의 정보를 수정하는 메서드
	// DB의 패스워드와 같은지 비교
	// DB의 패스워드와 내가 입력한 패스워드가 같다 => 실행문
	// DB의 패스워드와 내가 입력한 패스워드가 다르다 => 실행문
	public boolean modifyMember(MemberDTO mdto) {
		System.out.println("MemberService : modifyMember() 매서드 확인");
		
		// 1. DB 조회
		String DBpass = memberMapper.getPass(mdto.getId());
		
		if(DBpass.equals(mdto.getPw()) && DBpass != null) {
			// 내가 입력한 패스워드가 DB에 존재한다.
			return memberMapper.updateMember(mdto) == 1;
		}else {
			// 내가 입력한 패스워드가 DB에 존재하지 않을때
			return false;
		}
	}
	
	// 한 회원의 정보만 삭제하는 메서드
	public boolean oneDelete(String id) {
		System.out.println("MemberService : oneDelete() 매서드 확인");
		// 현재 deleteMember()라는 DAO의 결과값이 result에 0(삭제안됨) or 1(삭제됨) 로 담긴다.
		// memberdao.deleteMember(id) => 1(삭제됨) == 1 => 삭제됐다. (true)
		return memberMapper.deleteMember(id) == 1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
