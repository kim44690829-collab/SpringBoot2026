package com.green.book.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.green.book.dto.BookDTO;
import com.green.book.dto.MemberDTO;

@Repository
public class DataDAO {

	private Map<String, MemberDTO> memberDB = new HashMap<>();
	private Map<String, BookDTO> bookDB = new HashMap<>();

	public Map<String, MemberDTO> getMemberDB() {
		return memberDB;
	}

	public void setMemberDB(Map<String, MemberDTO> memberDB) {
		this.memberDB = memberDB;
	}

	public Map<String, BookDTO> getBookDB() {
		return bookDB;
	}

	public void setBookDB(Map<String, BookDTO> bookDB) {
		this.bookDB = bookDB;
	}

	// member 추가하는 메서드
	public void insertMember(MemberDTO mdto) {
		System.out.println("member insert");
		memberDB.put(mdto.getUserId(), mdto);
		printMember();
	}
	
	// member 검색하는 메서드
	public MemberDTO selectMember(MemberDTO mdto) {
		System.out.println("member select");
		MemberDTO loginMember = memberDB.get(mdto.getUserId());
		return loginMember;
	}
	
	// book 추가하는 메서드
	public void insertBook(BookDTO bdto) {
		System.out.println("book insert");
		bookDB.put(bdto.getIsbn(), bdto);
		printBook();
	}
	
	// book 검색하는 메서드
	public BookDTO selectBook(BookDTO bdto) {
		System.out.println("book select");
		BookDTO bookSelect = bookDB.get(bdto.getIsbn());
		return bookSelect;
	}
	
	public void printMember() {
		for(String mem : memberDB.keySet()) {
			MemberDTO mdto = memberDB.get(mem);
			System.out.println("id : " + mdto.getUserId());
			System.out.println("pw : " + mdto.getUserPw());
		}
	}
	public void printBook() {
		for(String book : bookDB.keySet()) {
			BookDTO bdto = bookDB.get(book);
			System.out.println("bookISBN : " + bdto.getIsbn());
			System.out.println("rentalId : " + bdto.getUserId());
		}
	}
}
