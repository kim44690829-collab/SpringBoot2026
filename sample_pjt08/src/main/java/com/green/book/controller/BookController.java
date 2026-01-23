package com.green.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.book.dao.DataDAO;
import com.green.book.dto.BookDTO;
import com.green.book.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	BookService BookS;
	
	@Autowired
	DataDAO bdao;
	
	// 책 대여 폼
	@GetMapping("/book")
	public String book() {
		return "book";
	}
	
	// 책 대여 완료 화면
	@PostMapping("/rentalInfo")
	public String rentInfo(BookDTO bdto, Model model) {
		
		BookS.addBook(bdto);
		
		model.addAttribute("bookName", bdto.getBookName());
		model.addAttribute("auther", bdto.getAuther());
		model.addAttribute("isbn", bdto.getIsbn());
		model.addAttribute("rentalName", bdto.getRentalName());
		model.addAttribute("userId", bdto.getUserId());
		model.addAttribute("rental", bdto.getRentalBook());
		model.addAttribute("return", bdto.getReturnBook());
		
		return "rentalInfo";
	}
	
	@GetMapping("/bookRental")
	public String bookRental() {
		return "rentalSearch";
	}
	
	

}
