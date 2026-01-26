package com.green.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // 데이터가 저장된 저장소
public class MemberDAO {
	
	// MySQL Driver 설치 및 JDBC 환경 설정 완료
	// 외부에서 DataSource를 DI로 삽입한다.
	@Autowired
	private DataSource dataSource;
	
	// 쿼리문 사용할 공간
	public int insertMember(MemberDTO mdto) {
		System.out.println("MemberDAO : insertMember() 매서드 확인");
		
		// 실무에서 쿼리문 작성시 대문자로 작성함.
		// no, reg_date, mod_date는 들어가지 않아도 괜찮기때문에 ()를 작성하고 안에 추가할 필드명만 작성한다.
		// 추가할 필드명이 정해져 있을 경우 반드시 (필드명1, 필드명2...) 필드명을 명시한다.
		// INSERT INTO user_member(id, pw, mail, phone) VALUES(?,?,?,?);
		String sql = "INSERT INTO user_member(id, pw, mail, phone) VALUES(?, ?, ?, ?)";
		int result = 0;
		
		// DB는 네트워크를 통해 자료를 가져오므로 try~catch() 구문을 이용한다.
		try(
				// Connection 클래스를 이용해 dataSource를 getConnection()해야한다.
				// Connection은 연결하는 자원으로 사용하고나면 반드시 반납을 해야함 => close();
				// 그런데 try안에 Connection문구를 사용하면 자동 close()됨
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			// ?,?,?,? 의 값을 대응해줘야한다.
			// input => 입력 => MemberDTO mdto
			pstmt.setString(1, mdto.getId());
			pstmt.setString(2, mdto.getPw());
			pstmt.setString(3, mdto.getMail());
			pstmt.setString(4, mdto.getPhone());
			
			// insert, delete, update 실행명령 => executeUpdate() 
			result = pstmt.executeUpdate();
			// executeUpdate() => insert, delete, update문을 실행하고나면 실행결과를 int 데이터 타입의 행의 개수로 반환한다.
			// insert 1건 성공 => 반환값 = 1
			// insert 0건 중복체크 => 반환값 = 0
			// update 3건 수정 => 반환값 = 3
			// delete 5건 삭제 => 반환값 = 5
			System.out.println("MemberDAO - insertMember : " + result);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 회원가입한 유저 모두 출력되는 메서드 작성
	public List<MemberDTO> allSelectMember() {
		System.out.println("MemberDAO : allSelectMember() 매서드 확인");
		String sql = "SELECT * FROM user_member";
		// list<E> 는 인터페이스 => 구현불가
		// ArrayList<>를 이용해야한다.
		List<MemberDTO> list = new ArrayList<>();
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				// select 구문은 executeQuery()를 실행한 결과를 ResultSet 객체에 담는다.
				ResultSet rs = pstmt.executeQuery();
				){
			// rs
			// no	id	pw	mail	phone	reg_date	mod_date
			// 1	1	1	1		1		2026~		2026~
			// 2	2	2	2		2		2026~		2026~
			// while문의 rs.next()는 한 행을 루프 돌고, 다음행을 돌고 다음 행이 없을때까지 루프
			// rs.next() => 다음 행의 값이 존재하면 true, 존재하지 않으면 false
			// while문은 rs.next()가 false가 되면 종료 
			while(rs.next()) {
				// mdto를 rs의 결과값을 저장하는 용도로 사용할 계획
				MemberDTO mdto = new MemberDTO();
				mdto.setNo(rs.getInt("no"));
				mdto.setId(rs.getString("id"));
				mdto.setPw(rs.getString("pw"));
				mdto.setMail(rs.getString("mail"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setReg_date(rs.getString("reg_date"));
				mdto.setMod_date(rs.getString("mod_date"));
				
				// ArrayList에 추가
				list.add(mdto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public boolean isMember(String id) {
		System.out.println("MemberDAO : isMember() 매서드 확인");
		return false;
	}
}
