package com.green.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.member.MemberDTO;

@Mapper
public interface MemberMapper {
	
	// 회원가입 insert
	public int signupInsert(MemberDTO mdto);
	
	// 회원가입 회원 전체 목록 출력
	public List<MemberDTO> allMember();
	
	// 회원가입시 아이디가 중복되는지 확인하기 위한 메서드
	public boolean isMember(String id);
}
