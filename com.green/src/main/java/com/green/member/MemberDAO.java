package com.green.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.green.HomeController;
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
	
	// --------------------------------- 2026-01-27 추가쿼리 작성--------------------------------------------------
	// 개인 한 사람의 정보를 검색하는 메서드
	public MemberDTO oneSelectMember(String id) {
		// log
		System.out.println("MemberDAO : oneSelectMember() 매서드 확인");
		// 반환받을 MemberDTO 객체 생성
		MemberDTO mdto = new MemberDTO();
		// sql 구문 작성
		String sql = "SELECT * FROM user_member WHERE id = ?";
		
		// 예외처리 try(자동 close()를 위해 connection 설정){} ~ catch(){}
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			// 실행구문
			// ? 대응을 먼저 작성해야한다.
			// ? => input에서 입력받을 값
			pstmt.setString(1, id);
			// select 문은 반드시 ResultSet 객체에 담는다.
			ResultSet rs = pstmt.executeQuery();
			// mdto.setId(~~) 담는다.
			// rs.next() 없이 값을 꺼내오면 항상 빈 DTO임 
			if(rs.next()) {
				mdto.setNo(rs.getInt("no"));
				mdto.setId(rs.getString("id"));
				mdto.setPw(rs.getString("pw"));
				mdto.setMail(rs.getString("mail"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setReg_date(rs.getString("reg_date"));
				mdto.setMod_date(rs.getString("mod_date"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mdto;
	}
	
	// 개인 한사람의 정보 수정
	public int updateMember(MemberDTO mdto) {
		System.out.println("MemberDAO : updateMember() 매서드 확인");
		int result = 0;
		
		String sql = "UPDATE user_member SET mail = ?, phone = ? WHERE id = ?";
		
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			// ? 3개 대응
			pstmt.setString(1, mdto.getMail());
			pstmt.setString(2, mdto.getPhone());
			pstmt.setString(3, mdto.getId());
			result = pstmt.executeUpdate();
			System.out.println("UPDATE result : " + result);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 개인 한 사람의 패스워드 리턴하는 쿼리
	public String getPass(String id) {
		System.out.println("MemberDAO : getPass() 매서드 확인");
		String pass="";
		String sql = "SELECT pw FROM user_member WHERE id = ?";
		
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			// ? 대응
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pass = rs.getString(1); // 패스워드 값에 저장된 매핑 인덱스
			}
			System.out.println("getPass pw : " + pass);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return pass;
	}
	
	// 한 사람 개인의 정보를 삭제하는 메서드 작성
	public int deleteMember(String id) {
		System.out.println("MemberDAO : deleteMember() 매서드 확인");
		int result = 0;
		
		String sql = "DELETE FROM user_member WHERE id = ?";
		
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			// ? 대응
			pstmt.setString(1, id);
			// 쿼리문 실행할때 
			// executeUpdate()는 delete, insert, update 가 사용
			// executeQuery()는 select가 사용
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public boolean isMember(String id) {
		System.out.println("MemberDAO : isMember() 매서드 확인");
		return false;
	}
}
