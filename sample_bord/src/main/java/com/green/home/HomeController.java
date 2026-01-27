package com.green.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({"", "/"})
	public String home() {
		System.out.println("HomeController : home() 확인");
		String nextPage = "home";
		return nextPage;
	}
}
