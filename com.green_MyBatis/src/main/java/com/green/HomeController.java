package com.green;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// http://localhost:8090 
	// http://localhost:8090/ 
	@GetMapping({"","/"})
	public String home() {
		// syso => log찍는 용도, 반드시 필요
		System.out.println("HomeController 확인");
		return "home";
	}
}
