package com.green;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

// @Repository => 데이터 저장소를 의미 / DAO객체를 생성할때 주로 사용
// DAO => 쿼리문의 집합(SQL) => 데이터를 직접 처리하는 객체
@Repository
public class MemberDAO {

	// 원래 DB 커넥션이 존재해야하지만 아직 DB를 엮지 않음 => HashMap<>으로 대체
	private Map<String, MemberDTO> memberDB = new HashMap<>();
	
	// insertMember
	public void insertMember(MemberDTO mdto) {
		System.out.println("회원 가입을 추가하는 메서드");
		memberDB.put(mdto.getId(), mdto);	
		printMember();
	}
	
	// selectMember
	public MemberDTO selectMember(MemberDTO mdto) {
		System.out.println("로그인의 정보를 확인하는 메서드");
		
		// id와 pw를 비교해서 같으면 로그인 성공 틀리면 로그인 실패
		MemberDTO loginMember = memberDB.get(mdto.getId());
		
		return loginMember;
	}
	
	// 회원 정보 출력
	public void printMember() {
		for(String key : memberDB.keySet()) {
			MemberDTO mdto = memberDB.get(key);
			System.out.println("id : " + mdto.getId());
			System.out.println("pw : " + mdto.getPw());
		}
		System.out.println();
	}
	
	
}
