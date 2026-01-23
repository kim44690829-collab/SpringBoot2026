package com.green.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.book.dao.DataDAO;
import com.green.book.dto.BookDTO;

@Service
public class BookService {
	@Autowired
	DataDAO dDao;
	
	// 책 대여
	public void addBook(BookDTO bdto) {
		System.out.println("책 대여 완료");
		dDao.insertBook(bdto);
	}
	
	public void selectBook(BookDTO bdto) {
		BookDTO bookSelect = dDao.selectBook(bdto);
		
		//if(bookSelect != null && )
	}
	
}
