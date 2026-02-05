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
		
		int result = reviewService.insertBoard(rbdto);
		
		if(result > 0) {
			return "redirect:/";
		}else {
			return "redirect:/reviewBoard/reviewWrite";
		}
	}
	
	@GetMapping("/")
	public String board(
			Model model, 
			@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="pageSize", defaultValue="5") int pageSize
			) {
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
}
