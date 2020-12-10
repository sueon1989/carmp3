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
//	단일 파라미터를 받는 생성자의 경우, 필요한 파라미터를 자동으로 주입
//	BoardServiceImpl(BoardMapper)
	private BoardMapper mapper;

//	목록(리스트)
	@Override
	public List<BoardVO> getList() {
		
		log.info("목록(리스트)......");
		return mapper.getList();
	}
	
//	등록 - insert 처리 (SelectKey 사용)
	@Override
	public void register(BoardVO board) {
		
		log.info("등록......"+board);
		mapper.insertSelectKey(board);
	}

//	조회 (한 행) - read (select) 처리
	@Override
	public BoardVO get(Long bno) {
		
		log.info("조회......"+bno);
		return mapper.read(bno);
	}
	
//	삭제 - delete 처리
	@Override
	public boolean remove(Long bno) {
		
		log.info("삭제......"+bno);
		return mapper.delete(bno) == 1;
	}

//	수정 - update 처리
	@Override
	public boolean modify(BoardVO board) {
		
		log.info("수정전......"+board);
		return mapper.update(board) == 1;
	}
	
//	게시글 삭제 개수
	@Override
	public int removeCount() {
		int deleteNum = mapper.deleteNum();
		log.info("게시글 삭제 개수......"+deleteNum);
		return deleteNum;
	}

//	작성자의 총 작성글 개수
	@Override
	public int writeCount(String writer) {
		int writeNum = mapper.writeNum(writer);
		log.info(writer +"의 총 작성글 개수: "+ writeNum);
		return writeNum;
	}

}
