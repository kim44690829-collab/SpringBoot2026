package com.green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductControll {

	@GetMapping("/product/order")
	public String order() {
		return "order";
	}
	
	@PostMapping("/product/orderResult")
	public String orderResult(
			@RequestParam("productName") String productName,
			@RequestParam("price") int price,
			@RequestParam("quantity") int quantity,
			@RequestParam("ordererName") String ordererName,
			Model model
			) {
		
		int totalPrice = price * quantity;
		
		model.addAttribute("productName", productName);
		model.addAttribute("price", price);
		model.addAttribute("quantity", quantity);
		model.addAttribute("ordererName", ordererName);
		model.addAttribute("totalPrice", totalPrice);
		
		return "orderResult";
	}
	
}
