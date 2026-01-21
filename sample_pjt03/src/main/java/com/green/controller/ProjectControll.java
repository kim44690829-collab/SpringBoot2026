package com.green.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProjectControll {

	@GetMapping("/book/rental")
	public String rental() {
		System.out.println("rental()");
		return "book";
	}
	
//	@GetMapping("/book/rentalProc")
//	public String rentalproc(
//			@RequestParam("bookName") String bookName,
//			@RequestParam("author") String author,
//			@RequestParam("isbn") String isbn,
//			@RequestParam("rentalName") String rentalName,
//			Model model
//			) {
//		System.out.println("rentalproc()");
//		
//		model.addAttribute("bookName",bookName);
//		model.addAttribute("author",author);
//		model.addAttribute("isbn",isbn);
//		model.addAttribute("rentalName",rentalName);
//		
//		return "rentalSearch";
//	}
	
	@GetMapping("/book/rentalProc")
	public String rentalproc(
			@RequestParam("bookName") ArrayList<String> bookName,
			@RequestParam("author") ArrayList<String> author,
			@RequestParam("isbn") ArrayList<String> isbn,
			@RequestParam("rentalName") ArrayList<String> rentalName,
			Model model
			) {
		System.out.println("rentalproc()");
	
		model.addAttribute("bookNames", bookName);
		model.addAttribute("authors",author);
		model.addAttribute("isbns",isbn);
		model.addAttribute("rentalNames",rentalName);

		
		
		return "rentalSearch";
	}
}
