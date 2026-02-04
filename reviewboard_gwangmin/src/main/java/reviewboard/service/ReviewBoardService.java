package reviewboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reviewboard.dto.ReviewBoardDTO;
import reviewboard.mapper.ReviewBoardMapper;

@Service
public class ReviewBoardService {
	
	@Autowired
	ReviewBoardMapper reviewBoardMepper;
	
	public int insertBoard() {
		System.out.println("ReviewBoardService : insertBoard() 매서드 확인");
		return reviewBoardMepper.insertBoard();
	}
	
	public List<ReviewBoardDTO> allSelect(int startRow, int pageSize){
		System.out.println("ReviewBoardService : allSelect() 매서드 확인");
		return reviewBoardMepper.allSelect(startRow, pageSize);
	}
	
	public ReviewBoardDTO oneSelect(int num) {
		System.out.println("ReviewBoardService : oneSelect() 매서드 확인");
		return reviewBoardMepper.oneSelect(num);
	}
	
	public int updateBoard(ReviewBoardDTO rbdto) {
		System.out.println("ReviewBoardService : updateBoard() 매서드 확인");
		return reviewBoardMepper.updateBoard(rbdto);
	}
	
	public int deleteBoard(int num) {
		System.out.println("ReviewBoardService : deleteBoard() 매서드 확인");
		return reviewBoardMepper.deleteBoard(num);
	}
	
}
