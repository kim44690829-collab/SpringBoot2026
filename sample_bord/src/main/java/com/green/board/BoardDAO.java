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
	private DataSource datasource;
	
	// 게시글 추가 => insert
	public int insertBoard(BoardDTO bdto) {
		System.out.println("BoardDAO : insertBoard() 메서드 확인");
		String sql = "INSERT INTO boardEasy(title, content, writer) VALUES(?,?,?)";
		int result = 0;
		
		try(
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setString(1, bdto.getTitle());
			pstmt.setString(2, bdto.getContent());
			pstmt.setString(3, bdto.getWriter());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 게시글 전체 출력
	public List<BoardDTO> allSelect(){
		System.out.println("BoardDAO : allSelect() 메서드 확인");
		List<BoardDTO> boardList = new ArrayList<>();
		// String sql = "SELECT id, title, content, writer, createdAt FROM board ORDER BY id DESC";
		String sql = "SELECT * FROM boardEasy ORDER BY id DESC";
		
		try(
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO bdto = new BoardDTO();
				bdto.setId(rs.getInt("id"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setWriter(rs.getString("writer"));
				bdto.setCreatedAt(rs.getString("createdAt"));
				boardList.add(bdto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return boardList;
	}
	
	// 한사람의 정보를 검색
	public BoardDTO oneSelect(int id) {
		System.out.println("BoardDAO : oneSelect() 메서드 확인");
		
		BoardDTO bdto = new BoardDTO();
		String sql = "SELECT * FROM boardEasy WHERE id = ?";
		
		try(
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				bdto.setId(rs.getInt("id"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setWriter(rs.getString("writer"));
				bdto.setCreatedAt(rs.getString("createdAt"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bdto;
	}
	
	// 한 사람의 데이터를 삭제하는 메서드
	public int oneDelete(int id) {
		System.out.println("BoardDAO : oneDelete() 메서드 확인");
		
		String sql = "DELETE FROM boardEasy WHERE id = ?";
		int result = 0;
		
		try(
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 게시글 수정하는 메서드
	public int modBoard(BoardDTO bdto) {
		System.out.println("BoardDAO : modBoard() 메서드 확인");
		int result = 0;
		String sql = "UPDATE boardEasy SET title = ?, content = ? WHERE id = ?";
		
		System.out.println("1" + bdto.getTitle());
		System.out.println("2" + bdto.getContent());
		System.out.println("3" + bdto.getId());
		try(
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setString(1, bdto.getTitle());
			System.out.println("1111111"  + bdto.getTitle());
			pstmt.setString(2, bdto.getContent());
			pstmt.setInt(3, bdto.getId());
			
			result = pstmt.executeUpdate();
			System.out.println("UPDATE result : " + result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 게시글을 검색하는 메서드
	public List<BoardDTO> searchBoard(String searchType, String searchKeyword){
		System.out.println("BoardDAO : searchBoard() 메서드 확인");
		
		List<BoardDTO> searchBoardList = new ArrayList<>();
		
		String sql = "";
		if("title".equals(searchType)) {
			sql = "SELECT * FROM boardEasy WHERE title LIKE ?";
		}else if("content".equals(searchType)) {
			sql = "SELECT * FROM boardEasy WHERE content LIKE ?";
		}else if("writer".equals(searchType)){
			sql = "SELECT * FROM boardEasy WHERE writer LIKE ?";
		}else {
			sql = "SELECT * FROM boardEasy WHERE createdAt LIKE ?";
		}
		
		try(
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			
			pstmt.setString(1, "%" + searchKeyword + "%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO bdto = new BoardDTO();
				bdto.setId(rs.getInt("id"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setWriter(rs.getString("writer"));
				bdto.setCreatedAt(rs.getString("createdAt"));
				
				searchBoardList.add(bdto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return searchBoardList;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
