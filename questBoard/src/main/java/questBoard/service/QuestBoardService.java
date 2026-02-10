package questBoard.service;

import java.util.List;

import questBoard.dto.QuestBoardDTO;

public interface QuestBoardService {

	// 게시글 작성 insert
	public void questBoardInsert(QuestBoardDTO qdto);
	
	// 게시글 목록 select
	public List<QuestBoardDTO> boardAllSelect();
	
	// 게시글 하나 상세보기 select
	public QuestBoardDTO boardOneSelect(int num);
	
	// 답글
	public void reWriteInsert(QuestBoardDTO qdto);
}
