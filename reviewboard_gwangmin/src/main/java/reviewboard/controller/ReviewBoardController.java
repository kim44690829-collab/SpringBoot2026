package reviewboard.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import reviewboard.dto.PageHandler;
import reviewboard.dto.ReviewBoardDTO;
import reviewboard.service.ReviewBoardService;

@Controller
public class ReviewBoardController {

	@Autowired
	ReviewBoardService reviewService;
	
	@GetMapping("/reviewBoard/write")
	public String reviewWrite() {
		System.out.println("ReviewBoardController : reviewWrite() 메서드 확인");
		return "/reviewBoard/reviewWrite";
	}
	
	@PostMapping("/reviewBoard/writePro")
	public String writePro(ReviewBoardDTO rbdto) {
		System.out.println("ReviewBoardController : writePro() 메서드 확인");
		int result = reviewService.insertBoard(rbdto);
		
		if(result > 0) {
			return "redirect:/reviewBoard/list";
		}else {
			return "redirect:/reviewBoard/reviewWrite";
		}
	}
	
	@GetMapping("/reviewBoard/list")
	public String board(
			Model model, 
			@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="pageSize", defaultValue="5") int pageSize
			) {
		System.out.println("ReviewBoardController : board() 메서드 확인");
		int totalCount = reviewService.countBoard();
		int pageBlock = 3;
		
		PageHandler ph = new PageHandler(totalCount, pageBlock, page, pageSize);
		
		List<ReviewBoardDTO> allBoard = reviewService.allSelect(ph.getStartRow(), pageSize);
		double result = reviewService.ratingAvg();
		
		DecimalFormat ratingAVG = new DecimalFormat("#.#");
		double resultNum = Double.parseDouble(ratingAVG.format(result)); 
		
		model.addAttribute("list", allBoard);
		model.addAttribute("ph", ph);
		model.addAttribute("avg", resultNum);
		
		return "/reviewBoard/reviewList";
	}
	
	@GetMapping("/reviewBoard/detail")
	public String reviewInfo(@RequestParam("num") int num, Model model) {
		System.out.println("ReviewBoardController : reviewInfo() 메서드 확인");
		
		reviewService.updateCount(num);
		ReviewBoardDTO rbdto = reviewService.oneSelect(num);
		
		model.addAttribute("detail", rbdto);
		
		String nextPage = "reviewBoard/reviewInfo";
		return nextPage;
	}
	
	@GetMapping("/reviewBoard/deletePro")
	public String boardDeletePro(@RequestParam("num") int num) {
		System.out.println("ReviewBoardController : boardDeletePro() 메서드 호출");
		
		boolean result = reviewService.deleteBoard(num);
		
		if(result) {
			return "redirect:/reviewBoard/list";
		}else{
			return "redirect:/reviewBoard/detail?num=" + num;
		}
		
	}
	
	@GetMapping("/reviewBoard/update")
	public String updateForm(@RequestParam("num") int num, Model model) {
		System.out.println("ReviewBoardController : updateForm() 메서드 호출");
		
		ReviewBoardDTO rbdto = reviewService.oneSelect(num);
		
		model.addAttribute("detail", rbdto);
		
		String nextPage = "reviewBoard/reviewMod";
		return nextPage;
	}
	
	@PostMapping("/reviewBoard/updatePro")
	public String updatePro(ReviewBoardDTO rbdto) {
		System.out.println("ReviewBoardController : updatePro() 메서드 호출");
		
		boolean result = reviewService.updateBoard(rbdto);
		
		if(result) {
			return "redirect:/reviewBoard/detail?num=" + rbdto.getNum();
		}else {
			return "redirect:/reviewBoard/update?num=" + rbdto.getNum();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
