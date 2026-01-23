package com.green;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddressController {
	
	// heap 메모리에 주소데이터를 담을 리스트가 필요
	// ArrayList<E> 
	private List<AddressDTO> addressList = new ArrayList<>();
	
	@GetMapping("/address")
	public String list(Model model) {
		model.addAttribute("list", addressList);
		return "/addressList";
	}
	
	// 주소 등록
	@PostMapping("/add-address")
	public String addr(AddressDTO adto) {
		// ArrayList 삽입
		// 삽입 메서드 : add(value)
		addressList.add(adto);
		// 현재 URL은 add-address인데 address로 이동하겠다.
		return "redirect:/address";
	}
	
}
