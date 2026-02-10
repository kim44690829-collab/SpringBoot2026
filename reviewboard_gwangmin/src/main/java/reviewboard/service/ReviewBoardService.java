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
	
	public int insertBoard(ReviewBoardDTO rbdto) {
		System.out.println("ReviewBoardService : insertBoard() 매서드 확인");
		return reviewBoardMepper.insertBoard(rbdto);
	}
	
	public List<ReviewBoardDTO> allSelect(int startRow, int pageSize){
		System.out.println("ReviewBoardService : allSelect() 매서드 확인");
		return reviewBoardMepper.allSelect(startRow, pageSize);
	}
	
	public int countBoard() {
		System.out.println("ReviewBoardService : countBoard() 매서드 확인");
		return reviewBoardMepper.countBoard();
	}
	
	public double ratingAvg() {
		System.out.println("ReviewBoardService : ratingAvg() 매서드 확인");
		Double result = reviewBoardMepper.ratingAvg(); 
		return (result == null) ? 0.0 : result;
	}
	
	public ReviewBoardDTO oneSelect(int num) {
		System.out.println("ReviewBoardService : oneSelect() 매서드 확인");
		return reviewBoardMepper.oneSelect(num);
	}
	
	public boolean updateBoard(ReviewBoardDTO rbdto) {
		System.out.println("ReviewBoardService : updateBoard() 매서드 확인");
		return reviewBoardMepper.updateBoard(rbdto) == 1;
	}
	
	public boolean deleteBoard(int num) {
		System.out.println("ReviewBoardService : deleteBoard() 매서드 확인");
		return reviewBoardMepper.deleteBoard(num) == 1;
	}
	
	public void updateCount(int num) {
		System.out.println("ReviewBoardService : updateCount() 매서드 확인");
		reviewBoardMepper.updateCount(num);
	}
	
}
