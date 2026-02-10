package reviewboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import reviewboard.dto.ReviewBoardDTO;

@Mapper
public interface ReviewBoardMapper {
	
	// 게시판 insert
	public int insertBoard(ReviewBoardDTO rbdto);
	
	// 게시판 전체 select
	// 게시판 전체 select일때만 paging이 되면 됨. 나머지는 하나 상세보기 혹은 업데이트, 삭제, 삽입 등임.
	public List<ReviewBoardDTO> allSelect(@Param("startRow") int startRow, @Param("pageSize") int pageSize);
	
	public int countBoard();
	
	public Double ratingAvg();
	
	// 게시판 하나 select
	public ReviewBoardDTO oneSelect(int num);
	
	// 게시판 update
	public int updateBoard(ReviewBoardDTO rbdto);
	
	// 게시판 delete
	public int deleteBoard(int num);
	
	// 조회수 증가
	public int updateCount(int num);
	
}
