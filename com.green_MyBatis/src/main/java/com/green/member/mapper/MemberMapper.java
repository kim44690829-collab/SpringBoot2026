package com.green.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.member.MemberDTO;

@Mapper // MemberMapper는 연결 역할을 한다. -> xml파일과 연결
public interface MemberMapper {
	// MemberDAO에서 만들었던 메서드를 추상메서드로 작성한다.
	// 설정된 메서드들은 IoC 컨테이너에 탑재된다.
	
	// 회원가입 메서드
	public int insertMember(MemberDTO mdto);
	
	// 회원가입한 유저 모두 출력되는 메서드 작성
	public List<MemberDTO> allSelectMember();
	
	// 개인 한 사람의 정보를 검색하는 메서드
	public MemberDTO oneSelectMember(String id);
	
	// 개인 한사람의 정보 수정
	public int updateMember(MemberDTO mdto);
	
	// 개인 한 사람의 패스워드 리턴하는 쿼리
	public String getPass(String id);
	
	// 한 사람 개인의 정보를 삭제하는 메서드 작성
	public int deleteMember(String id);
	
	// id 중복 체크 -> 입력된 id가 이미 존재하면 회원가입 실패 출력
	public boolean isMember(String id);
}
