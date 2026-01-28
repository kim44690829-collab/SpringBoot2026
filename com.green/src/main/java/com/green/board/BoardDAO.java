package com.green.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	
	@Autowired
	// application.properties 에서 설정된 환경의 MySQL 과 연결한다. 
	private DataSource datasource;

	// ----------------------------------------------------------------------------------
	// 쿼리문 작성시 KEY POINT
	// 1. 한 사람, 하나에 관련된 자료를 insert, select한다 => DTO객체에 담아 사용한다. => 데이터 타입 DTO
	// 2. 전체 목록, 전체 회원 등 여러개에 해당하는 자료 => List<E> 에 담아 사용한다. => 데이터 타입 List<E>
	// 3. 필드명 하나 select => 데이터 타입 String, int, boolean 등을 사용한다.
	// 4. 메서드 작성시 void는 return 사용 X
	// 5. 메서드 작성시 데이터 타입이 존재하면 반환값이 반드시 존재해야한다.
	// 6. try(){}catch(){} 구문을 사용
	// ----------------------------------------------------------------------------------
	
	// 하나의 게시글 작성하여 추가하는 쿼리문
	public void insertBoard(BoardDTO bdto) {
		System.out.println("2. BoardDAO : insertBoard() 매서드 호출");
		// 추가하는 쿼리문 insert into 테이블명 values()
		String sql = "INSERT INTO board(writer, subject, writerPw, content) VALUES(?, ?, ?, ?)";
		try(
				// datasource에 주입한 데이터베이스의 원본의 자료를 연결하세요.
				Connection conn = datasource.getConnection();
				// conn = (url, username, userPassword);
				// conn = (localhost:3306. "root", "12345678");
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			System.out.println("conn에 들어가는 것 : " + conn);
			System.out.println("pstmt에 들어가는 것 : " + pstmt);
			
			// 실행문 작성하는 곳
			// 제일 먼저 해야하는 일 => ? 대응 => ?는 가장 첫번째부터 1번 ~~~
			// input박스에서 작성자를 입력하면 그 입력한 값이 pstmt의 쿼리문에 들어감
			// pstmt.setString(1, "길동");
			// INSERT INTO board(writer, subject, writerPw, content) VALUES("길동", ?, ?, ?)
			pstmt.setString(1, bdto.getWriter());
			// pstmt.setString(2, "스프링 재밌어");
			// INSERT INTO board(writer, subject, writerPw, content) VALUES("길동", "스프링 재밌어", ?, ?)
			pstmt.setString(2, bdto.getSubject());
			// pstmt.setString(3, "1234");
			// INSERT INTO board(writer, subject, writerPw, content) VALUES("길동", "스프링 재밌어", "1234", ?)			
			pstmt.setString(3, bdto.getWriterPw());
			// pstmt.setString(4, "너무 재미있어");
			// INSERT INTO board(writer, subject, writerPw, content) VALUES("길동", "스프링 재밌어", "1234", "너무 재미있어")
			pstmt.setString(4, bdto.getContent());
			
			// sql문 실행 
			// insert, delete, update => executeUpdate()
			// select => executeQuery() / select문은 반드시 resultSet 객체에 담아서 출력한다.
			pstmt.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// insert 끝 select 시작--------------------------------------------------------------------------------------------
	// 전체 게시글 목록을 출력하는 쿼리문
	public List<BoardDTO> getAllBoard(){
		System.out.println("2. BoardDAO : getAllBoard() 메서드 호출");
		// List 인스턴스화
		List<BoardDTO> boardlist = new ArrayList<>();
		String sql = "SELECT * FROM board ORDER BY num DESC";
		try(
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			// 실행문
			// select 문은 ResultSet에 담는다.
			ResultSet rs = pstmt.executeQuery();
			// rs.next() => 다음 행이 존재하면 true, 없으면 false
			// rs.next() 를 사용하지 않으면 무조건 빈 DTO가 된다.
			while(rs.next()) {
				BoardDTO bdto = new BoardDTO();
				// bdto.setNum(rs.getInt(1)); // => 이렇게 써도 되는데 헷갈림 rs.getInt(1)의 1은 필드명이 몇번째인가를 나타내는 숫자임
				bdto.setNum(rs.getInt("num"));
				bdto.setWriter(rs.getString("writer"));
				bdto.setSubject(rs.getString("subject"));
				bdto.setWriterPw(rs.getString("writerPw"));
				bdto.setReg_date(rs.getString("reg_date"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setContent(rs.getString("content"));
				
				// ArrayList에 add
				boardlist.add(bdto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return boardlist;
	}
	
	// 하나의 게시글 상세정보 보기
	// readcount 누적해야함
	public BoardDTO getOneBoard(int num) {
		System.out.println("2. BoardDAO : getOneBoard() 메서드 호출");
		// BoardDTO 인스턴스화
		BoardDTO bdto = new BoardDTO();
		// readcount 누적 => 클릭할때마다
		String sql = "UPDATE board SET readcount = readcount + 1 WHERE num = ?";
		String sql2 = "SELECT * FROM board WHERE num = ?";
		
		// Connection 연결용 try~catch() 구문
		// 조회수 증가 sql try~catch() 구문
		// select sql2 try~catch() 구문
		try(Connection conn = datasource.getConnection();){
			// 조회수 증가
			try(PreparedStatement pstmt = conn.prepareStatement(sql);){
				// ? 대응
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
			}
			try(PreparedStatement pstmt = conn.prepareStatement(sql2);){
				// ? 대응
				pstmt.setInt(1, num);
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					bdto.setNum(rs.getInt("num"));
					bdto.setWriter(rs.getString("writer"));
					bdto.setSubject(rs.getString("subject"));
					bdto.setWriterPw(rs.getString("writerPw"));
					bdto.setReg_date(rs.getString("reg_date"));
					bdto.setReadcount(rs.getInt("readcount"));
					bdto.setContent(rs.getString("content"));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return bdto;
	}
	// select 끝 update 시작--------------------------------------------------------------------------------------------
	// 하나의 게시글을 수정하는 메서드
	public int updateBoard(BoardDTO bdto) {
		System.out.println("2. BoardDAO : updateBoard() 메서드 호출");
		
		int result = 0;
		
		// 수정할때 반드시 번호와 비밀번호가 일치해야 수정가능
		String sql = "UPDATE board SET subject = ?, content = ? WHERE num = ? AND writerPw = ?";
		try(
				// datasource에 주입한 데이터베이스의 원본의 자료를 연결하세요.
				Connection conn = datasource.getConnection();
				// conn = (url, username, userPassword);
				// conn = (localhost:3306. "root", "12345678");
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setString(1, bdto.getSubject());
			pstmt.setString(2, bdto.getContent());
			pstmt.setInt(3, bdto.getNum());
			pstmt.setString(4, bdto.getWriterPw());
			
			result = pstmt.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
