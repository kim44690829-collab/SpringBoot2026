package reviewboard.dto;

public class ReviewBoardDTO {
	private int num; // auto_increment
	private String title; 
	private String writer; 
	private int rating;
	private String content;
	private String reg_date; // 기본값 오늘, 현재시간
	private int readCount; // 기본값 0
}
