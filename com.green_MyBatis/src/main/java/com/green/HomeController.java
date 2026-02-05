package com.green;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.green.carproduct.CarProductDTO;
import com.green.carproduct.CarProductService;

@Controller
public class HomeController {

	@Autowired
	CarProductService carProductservice;
	
	// http://localhost:8090 
	// http://localhost:8090/ 
	@GetMapping({"","/"})
	public String home(Model model) {
		System.out.println("HomeController 확인");
		
		// List<CarProductDTO>
		// carProductservice.getAllCarProduct() -> DB의 한 행씩 꺼내서 ArrayList에 넣는 역할
		List<CarProductDTO> carlist = carProductservice.getAllCarProduct();
		
		// model.addAttribute()에 담아서 home으로 보낸다.
		// 단 model은 한번만 담아서 내보내면 다른 페이지로는 못감
		model.addAttribute("carlist", carlist.get(0).getPublicService());
		return "home";
	}
}
