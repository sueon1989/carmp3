package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

//	@Autowired
//	���� �Ķ���͸� �޴� �������� ���, �ʿ��� �Ķ���͸� �ڵ����� ����
//	BoardServiceImpl(BoardMapper)
	private BoardMapper mapper;

//	���(����Ʈ)
	@Override
	public List<BoardVO> getList() {
		
		log.info("���(����Ʈ)......");
		return mapper.getList();
	}
	
//	��� - insert ó�� (SelectKey ���)
	@Override
	public void register(BoardVO board) {
		
		log.info("���......"+board);
		mapper.insertSelectKey(board);
	}

//	��ȸ (�� ��) - read (select) ó��
	@Override
	public BoardVO get(Long bno) {
		
		log.info("��ȸ......"+bno);
		return mapper.read(bno);
	}
	
//	���� - delete ó��
	@Override
	public boolean remove(Long bno) {
		
		log.info("����......"+bno);
		return mapper.delete(bno) == 1;
	}

//	���� - update ó��
	@Override
	public boolean modify(BoardVO board) {
		
		log.info("������......"+board);
		return mapper.update(board) == 1;
	}
	
//	�Խñ� ���� ����
	@Override
	public int removeCount() {
		int deleteNum = mapper.deleteNum();
		log.info("�Խñ� ���� ����......"+deleteNum);
		return deleteNum;
	}

//	�ۼ����� �� �ۼ��� ����
	@Override
	public int writeCount(String writer) {
		int writeNum = mapper.writeNum(writer);
		log.info(writer +"�� �� �ۼ��� ����: "+ writeNum);
		return writeNum;
	}

}
