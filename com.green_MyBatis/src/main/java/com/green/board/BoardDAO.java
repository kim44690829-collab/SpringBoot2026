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
   
	// application.properties 설정된 환경의 MySQL과 연결한다라는 의미
	@Autowired
	private DataSource datasource;
	
	// 쿼리문 작성시 KeyPoint
	// 1. 한사람, 하나에 관련된 자료를 insert, select할때는
	//    => DTO객체에 담아 사용한다., 고로데이터 타입이 DTO 임
	// 2. 전체목록, 전체회원... 여러개의 해당하는 자료는
	//    => List<E> list = new ArrayList<E> 업캐스팅
	// 3. 필드명하나 select 할때 
	//     => 데이터타입 : String, int, boolean등을 사용함
	// 4. 메소드 작성시 void는 return사용안함
	// 5. 메소드 작성시 데이터 타입이 존재하면 반환값 retrun필요함
	// 6. try(){}~catch(){} 사용
	
	//하나의 게시글 작성하여 추가하는 쿼리문---------------------------------
	public void insertBoard(BoardDTO bdto) {
		System.out.println("2)BoardDAO insertBoard()메소드 호출 ");
		
		//추가하는 쿼리문 insert into 테이블명 values()
		String sql = "INSERT INTO board(writer,subject,writerPw,content) values(?,?,?,?)";
		try(
				// datasource 주입한 데이터베이스 원본의 자료들을 연결하세요
				Connection conn = datasource.getConnection();
				// conn = (url, username, userPassword)
				// conn = (localhost:3306,"root","123456768")
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			
			     // 실행문 작성하는 곳
			    //  제일 먼저 할일 ? 대응 => ?는 제일 첫번째 부터 1, 2, 3, ~~~~
			    //  input 박스 => user : 길동, 스프링재미있어, 1234, 너무재미있어~
			    //  pstmt.setString(1,"길동")
			    // INSERT INTO board(writer,subject,writerPw,content) values("길동","스프링재미있어","1234","너무재미있어~")
			    pstmt.setString(1,bdto.getWriter());
			    pstmt.setString(2,bdto.getSubject());
			    pstmt.setString(3,bdto.getWriterPw());
			    pstmt.setString(4,bdto.getContent());
			    // sql문 실행 , insert, delete, update => executeUpdate()
			    // select문 실행만 => executeQuery()
			    // select문은 반드시 ResultSet객체에 담아 출력한다.
			    pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 전체 게시글 목록을 출력하는 쿼리문
	public List<BoardDTO> getAllBoard(){
		System.out.println("2)BoardDAO getAllBoard()메소드 호출 ");
		// List<> 인스턴스화 한다.
		List<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		// sql
		String sql = "SELECT * FROM board ORDER BY num DESC";
		try(
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			    //실행문 select문은 ResultSet객체 담기
			    ResultSet rs = pstmt.executeQuery();
			    // rs.next() => 다음의 행이 존재하면 true, 아니면 false
			    // rs.next() 무조건 빈DTO가 생성됨을 주의하자.
			    while(rs.next()) {
			    	// BoardDTO를 인스턴스화 한다.
			    	BoardDTO bdto = new BoardDTO();
			    	bdto.setNum(rs.getInt("num"));
			    	bdto.setWriter(rs.getString("writer"));
			    	bdto.setSubject(rs.getString("subject"));
			    	bdto.setWriterPw(rs.getString("writerPw"));
			    	bdto.setReg_date(rs.getString("reg_date").toString());
			    	bdto.setReadcount(rs.getInt("readcount"));
			    	bdto.setContent(rs.getString("content"));
			    	boardlist.add(bdto);
			    }
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return boardlist;
	}
	
	// 하나의 게시글 상세 정보 보기
	// Readcount 누적하여 조회수를 증가하는 메소드도 함께 작성한다.
	public BoardDTO getOneBoard(int num) {
		System.out.println("2)BoardDAO getOneBoard()메소드 호출 ");
		// BoardDTO를 인스턴스화 한다.
		BoardDTO bdto = new BoardDTO();
		// readcount 클릭할 때 마다 1씩 누적하는 sql문작성
		String sql ="UPDATE board SET readcount=readcount+1 WHERE num=?";
		String sql2 = "SELECT * FROM board WHERE num=?";
		
		//Connection연결용 try~catch()구문
		// 조회수 증가 sql try~catch()구문
		// 하나의 게시글 정보 가져오는 sql2  try~catch()구문
		try(Connection conn = datasource.getConnection();)
		{
			// 조회수 증가 
			try(PreparedStatement pstmt=conn.prepareStatement(sql)){
				// ?대응
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
			}
			// 하나의 게시글 정보
			try(PreparedStatement pstmt = conn.prepareStatement(sql2)){
				//?대응
				pstmt.setInt(1, num);
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
				bdto.setNum(rs.getInt("num"));
			    	bdto.setWriter(rs.getString("writer"));
			    	bdto.setSubject(rs.getString("subject"));
			    	bdto.setWriterPw(rs.getString("writerPw"));
			    	bdto.setReg_date(rs.getString("reg_date").toString());
			    	bdto.setReadcount(rs.getInt("readcount"));
			    	bdto.setContent(rs.getString("content"));	
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bdto;
	}
	
	// 하나의 게시글을 수정하는 메소드
	public int updateBoard(BoardDTO bdto) {
		System.out.println("2)BoardDAO updateBoard()메소드 호출 ");
		
		int result = 0; // 수정되면 1, 아니면 0
		// 수정할때 반드시, 번호와 비밀번호가 일치해야만 수정하는 쿼리 
		String sql ="UPDATE board SET subject=?,content=? WHERE num=? AND writerPw=?";
		try(
				
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			
		        // ? 대응
			    pstmt.setString(1,bdto.getSubject());
			    pstmt.setString(2,bdto.getContent());
			    pstmt.setInt(3,bdto.getNum());
			    pstmt.setString(4,bdto.getWriterPw());
			    result  =  pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//----------------- 2026년 01월 29일 시작부분------------------
	// 게시글 작성시 비밀번호 입력하였기때문에 => 삭제시에도 비밀번호와 번호가 일치하는지 체크
	public int deleteBoard(int num, String writerPw) {
		System.out.println("2)BoardDAO deleteBoard()메소드 호출 ");
		
		int result = 0;
		// 삭제 : delete from 테이블명 where 조건;
		String sql ="DELETE FROM board WHERE num=? AND writerPw=?";
		try(Connection conn=datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			//실행문
			// ? 대응
			pstmt.setInt(1, num);
			pstmt.setString(2, writerPw);
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 내용또는 제목으로 게시글 검색하는 메소드
	// 검색메소드 반드시, searchType, searchKeyword매개변수 필요
	public List<BoardDTO> getSearchBoard(String searchType, String searchKeyword){
		
		//List<>인스턴스
		List<BoardDTO> blist = new ArrayList<>();
		
		//sql
		String sql="";
		if("subject".equals(searchType)) {
			// subject의 검색 부분
			// 입력하는 문자를 포함하는 검색 명령어
			// select 필드명 from 테이블명 where 검색필드명 like %키워드%;
			sql ="SELECT * FROM board WHERE subject LIKE ? ORDER BY num DESC";
		}else {
			// content의 검색 부분
			sql ="SELECT * FROM board WHERE content LIKE ? ORDER BY num DESC";
		}
		
		try(Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			// ? 대응
			// select => 제목, input => 감사
			// select * from board where subject like %감사%;
			pstmt.setString(1, "%" + searchKeyword + "%");
			// select문 ResultSet 객체에 담는다
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// BoardDTO 인스턴스화 한다.
				BoardDTO bdto = new BoardDTO();
				
				bdto.setNum(rs.getInt("num"));
			    	bdto.setWriter(rs.getString("writer"));
			    	bdto.setSubject(rs.getString("subject"));
			    	bdto.setWriterPw(rs.getString("writerPw"));
			    	bdto.setReg_date(rs.getString("reg_date"));
			    	bdto.setReadcount(rs.getInt("readcount"));
			    	bdto.setContent(rs.getString("content"));
			    	
			    	// List<> add()추가하기
			    	blist.add(bdto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return blist;
	}
	
	
	
	
	
}
