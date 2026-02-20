package com.green;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.green.carproduct.CarProductDTO;
import com.green.carproduct.CarProductService;
import com.green.member.MemberDTO;
import com.green.member.MemberService;

import jakarta.servlet.http.HttpSession;

// @RestController는 @Controller + @ResponseBody를 합친 어노테이션
// => 컨트롤러 역할 + 데이터를 JSON으로 응답해서 사용한다.
// @ResponseBody는 메서드가 변환하는 데이터를 HTML 뷰를 찾는 용도가 아닌 데이터(JSON) 그 자체로 응답받아 직접 사용하겠다는 의미
// @RestController 하나만 맨 위에 적어주면 모든 메서드는 @ResponseBody를 붙이지 않아도 된다.

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	CarProductService carProductservice; // carList 메서드
	
	@Autowired
	MemberService memberservice;
	
	// 자동차 리스트를 JSON으로 변환하는 API
	@GetMapping("/cars")
	public List<CarProductDTO> getCarList(){
		System.out.println("ApiController : getCarList() 메서드 확인");
		
		// DB 에서 데이터를 가져와서 그대로 리턴 (Springboot 가 자동으로 JSON배열로 변환)
		return carProductservice.getAllCarProduct();
		
	}
	
	// 회원가입 API (POST 방식)
	// @RequestBody : 리액트에서 보낸 JSON데이터를 자바 객체로 자동 변환해준다. 
	@PostMapping("/member/signup")
	public int signup(@RequestBody MemberDTO mdto) {
		System.out.println("ApiController : signup() 메서드 확인");
		return memberservice.signupConfirm(mdto);
	}
	
	// 로그인 메서드 - 리액트에서 보내서 스프링에서 받는용도
	@PostMapping("/member/login")
	public MemberDTO login(@RequestBody MemberDTO mdto, HttpSession session) {
		System.out.println("ApiController : login() 메서드 확인");
		
		MemberDTO loginUser = memberservice.loginConfirm(mdto);
		
		if(loginUser != null) {
			// 세션에 담기
			session.setAttribute("loginUser", loginUser.getId());
		}
		
		// 리액트
		return  loginUser;
	}
	
	// 로그아웃
	@GetMapping("/member/logout")
	public int logout(HttpSession session) {
		System.out.println("ApiController : logout() 메서드 확인");
		session.invalidate();
		return 1; // 성공 신호
	}
	
	// 한 사람의 개인 정보를 조회하는 메서드 - select
	@GetMapping("/member/myinfo")
	public MemberDTO myInfo(HttpSession session) {
		System.out.println("ApiController : myInfo() 메서드 확인");
		// 다운캐스팅
		String id = (String) session.getAttribute("loginUser");
		
		MemberDTO loginUser = memberservice.oneSelect(id);
		
		if(id == null) {
			// null이면 로그인안됨
			return null;
		}
		
		return loginUser;
	}
	
	// 한 사람의 개인 정보를 삭제하는 컨트롤러
	// 삭제하다 => DeleteMapping
	@DeleteMapping("/member/delete")
	public int delete(HttpSession session) {
		System.out.println("ApiController : delete() 메서드 확인");
		String id = (String) session.getAttribute("loginUser");
		if(id == null) {
			return 0;
		}
		// 삭제 서비스 메서드
		boolean result = memberservice.oneDelete(id);
		if(result) {
			session.invalidate(); // 세션 삭제
			return 1;
		}else {
			return 0;
		}
	}
	
	// 이미지 React에서 업로드해서 DTO에 한반에 받기
	// @ModelAttribute 는 스프링 프레임워크에서 클라이언트가 보낸 데이터를 자바 객체(DTO)로 자동 바인딩해주는 어노테이션이다.
	@PostMapping("/cars/insert")
	public int insertCarProduct(
			@ModelAttribute CarProductDTO cdto,
			@RequestParam("uploadFile") MultipartFile file
			) throws Exception {
		System.out.println("자동차 등록 요청");
		
		// 저장 경로
		String savePath = "C:/Spring_Boot/com.green_MyBatis/frontend/public/img/car/";
		
		// 저장할 경로가 존재하지 않으면 자동으로 생성
		File dir = new File(savePath);
		
		// dir이 존재하지 않으면 (!dir.exists()) 생성해라 (dir.mkdirs();)
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String fileName = "";
		
		if(!file.isEmpty()) {
			// 사용자가 올린 파일명을 가져온다.
			String originalName = file.getOriginalFilename();
			
			// 파일명 중복해서 입력되지 않도록 UUID 클래스 이용
			// UUID => 36글자까지 랜덤하게 출력
			fileName = UUID.randomUUID().toString().substring(0,4) + "_" +  originalName;
			
			// 파일 전송
			File saveFile = new File(savePath + fileName);
			file.transferTo(saveFile);
		}
		
		// DTO 중에 setImg()에 파일명만 set
		cdto.setImg(fileName);
		
		// DB에 저장
		carProductservice.insertCarProduct(cdto);
		
		return 1;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
