package com.green;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.carproduct.CarProductDTO;
import com.green.carproduct.CarProductService;

// @RestController는 @Controller + @ResponseBody를 합친 어노테이션
// => 컨트롤러 역할 + 데이터를 JSON으로 응답해서 사용한다.
// @ResponseBody는 메서드가 변환하는 데이터를 HTML 뷰를 찾는 용도가 아닌 데이터(JSON) 그 자체로 응답받아 직접 사용하겠다는 의미
// @RestController 하나만 맨 위에 적어주면 모든 메서드는 @ResponseBody를 붙이지 않아도 된다.

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	CarProductService carProductservice; // carList 메서드
	
	// 자동차 리스트를 JSON으로 변환하는 API
	@GetMapping("/cars")
	public List<CarProductDTO> getCarList(){
		System.out.println("ApiController : getCarList() 메서드 확인");
		
		// DB 에서 데이터를 가져와서 그대로 리턴 (Springboot 가 자동으로 JSON배열로 변환)
		return carProductservice.getAllCarProduct();
		
	}
}
