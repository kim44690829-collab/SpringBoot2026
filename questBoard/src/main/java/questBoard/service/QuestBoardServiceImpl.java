package questBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questBoard.dto.QuestBoardDTO;
import questBoard.mapper.QuestBoardMapper;

@Service
public class QuestBoardServiceImpl implements QuestBoardService{

	@Autowired
	QuestBoardMapper questBoardMapper;
	
	@Override
	public void questBoardInsert(QuestBoardDTO qdto) {
		System.out.println("QuestBoardServiceImpl : questBoardInsert() 메서드 확인");
		questBoardMapper.questBoardInsert(qdto);
	}

	@Override
	public List<QuestBoardDTO> boardAllSelect() {
		System.out.println("QuestBoardServiceImpl : boardAllSelect() 메서드 확인");
		return questBoardMapper.boardAllSelect();
	}

	@Override
	public QuestBoardDTO boardOneSelect(int num) {
		System.out.println("QuestBoardServiceImpl : boardOneSelect() 메서드 확인");
		return questBoardMapper.boardOneSelect(num);
	}

	@Override
	public void reWriteInsert(QuestBoardDTO qdto) {
		System.out.println("QuestBoardServiceImpl : reWriteInsert() 메서드 확인");
		questBoardMapper.reWriteInsert(qdto);
	}

	
	
}
