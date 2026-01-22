package com.green.book.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.book.dto.BookDTO;

@Controller
public class BookController {
	
	// 책 대여 폼
	@GetMapping("/book")
	public String book() {
		return "book";
	}
	
	// 책 대여 완료 화면
	@PostMapping("/rentalInfo")
	public String rentInfo(BookDTO bdto, Model model) {
		
//		Date now = new Date();
//		SimpleDateFormat changes = new SimpleDateFormat("yyyy-MM-dd");
//		
//		Date now2 = new Date();
//		SimpleDateFormat changes2 = new SimpleDateFormat("yyyy-MM-dd");
		
		model.addAttribute("bookName", bdto.getBookName());
		model.addAttribute("auther", bdto.getAuther());
		model.addAttribute("isbn", bdto.getIsbn());
		model.addAttribute("rentalName", bdto.getRentalName());
		model.addAttribute("userId", bdto.getUserId());
//		model.addAttribute("rental", changes.format(now));
//		model.addAttribute("return", changes2.format(now2));
		
		return "rentalInfo";
	}
	
	@GetMapping("/bookRental")
	public String bookRental() {
		return "rentalSearch";
	}

}
