package com.green.board;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
		String sql = "INSERT INTO board(title, content, writer) VALUES(?,?,?)";
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
}
