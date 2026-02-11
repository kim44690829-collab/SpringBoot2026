package questBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import questBoard.dto.QuestBoardDTO;

@Mapper
public interface QuestBoardMapper {

	
	// 게시글 작성 insert
	public void questBoardInsert(QuestBoardDTO qdto);
	// 게시글 목록 select
	public List<QuestBoardDTO> boardAllSelect();
	// 게시글 하나 상세보기 select
	public QuestBoardDTO boardOneSelect(int num);
	// 게시글 답글 insert
	public void reWriteInsert(QuestBoardDTO qdto);
	// 게시글 답변여부 체크
	public int ansChc(QuestBoardDTO qdto);
}
