package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.member.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardservice;

	// 1. 게시글 작성 form 화면으로 이동하는 핸들러
	@GetMapping("/board/write")
	public String boardWriteForm(){
		System.out.println("1. BoardController : boardWriteForm() 메서드 호출");
		String nextPage = "board/boardWrite_form";
		return nextPage;
	}
	
	// 2. form에서 입력한 데이터를 DB에 영구 저장 => insert하는 controller 가 필요
	@PostMapping("/board/writePro")
	public String boardWritePro(BoardDTO bdto, HttpSession session) {
		System.out.println("1. BoardController : boardWritePro() 메서드 호출");
		
		// session.setAttribute("loginmember" , ?) => 로 저장한 데이터를 꺼내와야 함
		// 세션에서 값을 꺼내오는 메서드 session.getAttribute("loginmember")
		// Session 은 자바의 Object 최상위 객체이므로 다운캐스팅 하여야한다.
		// 로그인 ID = admin9867 => 로그인된 admin9867의 정보가 MemberDTO의 타입으로 loginedMember에 저장된다.
		MemberDTO loginedMember = (MemberDTO)session.getAttribute("loginmember");
		
		// 로그인 정보가 존재하는지 체크하는 코드가 필요
		if(loginedMember != null) {
			// 현재 로그인된 id는 loginedMember.getid()
			bdto.setId(loginedMember.getId());
			System.out.println("DB에 저장될 ID 확인 : " + loginedMember.getId());
		}else {
			System.out.println("로그인 실패");
			return "redirect:/member/login";
		}
		
		// 서비스의 addBoard()메서드 호출해서 DB에 저장
		boardservice.addBoard(bdto);
		
		// 저장 후에는 게시판 목록으로 페이지 이동(redirect)
		return "redirect:/board/list";
	}
	
//	// 3. DB에서 전체 게시글 목록 select로 검색해서 추출 -> 모델(model) 객체에 담는다. => 전체 목록 화면으로 이동 후 뿌린다.
//	@GetMapping("/board/list")
//	public String boardList(Model model) {
//		System.out.println("1. BoardController : boardList() 메서드 호출");
//		List<BoardDTO> boardlist = boardservice.allBoard();
//		model.addAttribute("list", boardlist);
//		
//		String nextPage = "board/boardList";
//		return nextPage;
//	}
	// 3. DB에서 전체 게시글 목록 select로 검색해서 추출 -> 모델(model) 객체에 담는다. => 전체 목록 화면으로 이동 후 뿌린다. 
	// 8. 검색을 위한 boardList 커스텀 하기
	@GetMapping("/board/list")
	public String boardList(
			Model model, 
			@RequestParam(value="searchType", required=false)  String searchType,
			@RequestParam(value="searchKeyWord" , required=false) String searchKeyWord,
			// 1. 페이지 번호 => 1부터 시작 - 초기값 1로 정의
			@RequestParam(value="page", defaultValue = "1") int page,
			// 2. 페이지 사이즈 => 한 화면에 보여지는 게시글의 수 - 5로 정의
			@RequestParam(value="pageSize", defaultValue = "5") int pageSize
			) {
		System.out.println("1. BoardController : boardList() 메서드 호출");
		
		// 3. 전체 게시글의 개수인 totalCnt 메서드 가져오기
		int totalCnt;
//		int totalCnt = boardservice.getSearchCount(searchType, searchKeyWord);
		if(searchType != null && !searchKeyWord.trim().isEmpty()) {
			// 검색이 성공한 경우 검색한 결과에 해당하는 개수 반환
			totalCnt = boardservice.getSearchCount(searchType, searchKeyWord);
		}else {
			// 검색을 하지 않은 경우 전체 게시글의 개수 반환
			totalCnt = boardservice.getAllCount();
		}
		
		// 4. PageHandler 클래스를 접근하기위해 인스턴스화
//		PageHandler ph;
		// 검색한 경과 totalCnt 가 1개라면
					//						1		1		5
		PageHandler ph = new PageHandler(totalCnt, page, pageSize);;
		
		List<BoardDTO> boardlist;
		
		
		// 검색 종료 후 => 검색 내용이 list 나오기
		if(searchType != null && !searchKeyWord.trim().isEmpty()) {
			// BoardDAO에 검색 메서드(getSearchBoard())로 접근
			// service에서 serchBoard() 메서드 호출
			// 검색되었을때 검색된 리스트반환하는 메서드로 교체
			boardlist = boardservice.getSearchPageList(searchType, searchKeyWord, ph.getStartRow(), pageSize);
		}else {
			// 검색하지 않고 전체 보기 list
			// boardlist = boardservice.allBoard() => 페이징이 안된 전체 리스트가 나와서 사용불가
			// 검색하지 않은 게시글 전체 반환
			boardlist = boardservice.getPageList(ph.getStartRow(), pageSize);
		}
		
		// 검색하지 않고 전체보기 list 나오기
		model.addAttribute("list", boardlist);
		// PageHandler 클래스를 model에 담아서 html로 보내야한다. => 그래야  UI화면에 페이징을 그릴 수 있다.
		model.addAttribute("ph", ph);
		// 검색 타입과 검색 키워드를 UI에 넘겨주지 않으면 오류
		// 반드시 searchType, searchKeyWord를 넘겨줘야한다. - boardList.html
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchKeyWord", searchKeyWord);
		
		String nextPage = "board/boardList";
		return nextPage;
	}
	
	// 4. 하나의 게시글 상세정보 확인 핸들러
	// num을 받아 해당 게시글을 DB에서 조회하고 상세정보를 저장하는 컨트롤러 
	@GetMapping("/board/boardInfo")
	public String boardInfo(@RequestParam("num") int num, Model model) {
		System.out.println("1. BoardController : boardInfo() 메서드 호출");
		
		boolean result  = boardservice.oneBoardChange(num);
		BoardDTO oneboardInfo = boardservice.oneBoardSearch(num);
		
		if(result) {
			model.addAttribute("oneboard", oneboardInfo);
		}
		model.addAttribute("oneboard", oneboardInfo);
		String nextPage = "board/boardInfo";
		return nextPage;
	}
	
	// 5. 게시글 수정 form으로 이동하는 controller
	@GetMapping("/board/update")
	public String boardUpdateForm(@RequestParam("num") int num, Model model) {
		System.out.println("1. BoardController : boardUpdateForm() 메서드 호출");
		
		// 기존에 하나의 게시글을 불러오는 쿼리를 이용하여 게시글 하나를 불러와서 수정
		BoardDTO oneboardInfo = boardservice.oneBoardSearch(num);
		model.addAttribute("oneboard", oneboardInfo);
		
		String nextPage = "board/boardUpdate_form";
		return nextPage;
	}
	
	// 6. 하나의 게시글 수정을 처리하는 controller
	@PostMapping("/board/updatePro")
	public String boardUpdatePro(BoardDTO bdto, Model model) {
		System.out.println("1. BoardController : boardUpdatePro() 메서드 호출");
		
		boolean result = boardservice.modifyBoard(bdto);
		// 수정 완료 => true, 수정 미완료 => false
		if(result) {
			// 수정 성공시 리스트
			return "redirect:/board/list";
		}else {
			// 수정 실패시 현재 수정하고있는 form => num을 가진 상태여야함
			return "redirect:/board/update?num=" + bdto.getNum();
		}
		
	}
	
	// 7. 하나의 게시글 삭제를 처리하는 controller
	// 현재 boardInfo에 존재 => 삭제를 누르면 삭제 => 삭제 후 리스트로 이동, 삭제 실패시 boardInfo에 머무름
	@GetMapping("/board/deletePro")
	public String boardDeletePro(@RequestParam("num") int num, @RequestParam("writerPw") String writerPw) {
		System.out.println("1. BoardController : boardDeletePro() 메서드 호출");
		
		// boardService에서 removeBoard는 boolean으로 return했기때문에 boolean으로 받아야함
		boolean result = boardservice.removeBoard(num, writerPw);
		
		if(result) {
			// 삭제 성공시 list로 이동
			return "redirect:/board/list";
		}else{
			// 삭제 실패시 상세페이지
			return "redirect:/board/boardInfo?num=" + num;
		}
		
	}
	
	// 로그인된 나의 게시글 목록을 검색하는 핸들러
	@GetMapping("/board/mypage")
	public String myBoardList(
			Model model, 
			HttpSession session, 
			@RequestParam(value = "page", defaultValue="1") int page) {
		
		// 세션을 이용해서 로그인된 아이디 정보를 가져옴
		// ID가 있는 행 전체를 가지고와서 담아줘야해서 MemberDTO를 데이터 타입으로 가져와야함
		// session은 Object의 바로 밑의 최상위 객체임으로 MemberDTO에 넣기 위해 다운 캐스팅
		// MemberDTO loginId에는 MemberDTO에 존재하는 모든 필드가 들어가므로 ID만 뽑아내기 위해서는 loginId.getId를 해야함
		MemberDTO loginId = (MemberDTO)session.getAttribute("loginmember");
		
		// 로그인 실패 또는 로그인 안된 상태
		if(loginId == null) {
			System.out.println("로그인 정보가 없으니 로그인 페이지로 이동합니다.");
			return "redirect:/member/login";
		}
		
		int pageSize = 5;
		// 로그인된 내 게시글 개수 조회
		int totalCnt = boardservice.getMyBoardCount(loginId.getId());
		// PageHandler 클래스 인스턴스화
		PageHandler ph = new PageHandler(totalCnt, page, pageSize);
		
		// 로그인된 내 게시글의 목록
		List<BoardDTO> mylist = boardservice.getMyBoardList(loginId.getId(), ph.getStartRow(), pageSize);
		
		model.addAttribute("list",mylist);
		model.addAttribute("ph", ph);
		
		return "/board/mypage";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
