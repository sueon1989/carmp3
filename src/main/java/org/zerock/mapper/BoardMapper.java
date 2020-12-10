package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;

public interface BoardMapper {
	
	// 게시글 목록(리스트)
//	@Select("select * from tbl_board where bno>0")
	public List<BoardVO> getList();
	
	// 게시글 등록 - insert 처리
	public void insert(BoardVO board);

	// 게시글 등록2 - @SelectKey 사용 (자동으로 추가되는 PK 값 확인)
	public void insertSelectKey(BoardVO board);
	
	// 게시글 조회 (한 행) - read (select) 처리
	public BoardVO read(Long bno);	// 객체 타입으로 넣어주어야 함 (long이 아닌 Long)
	
	// 게시글 삭제 - delete 처리
	public int delete(Long bno);

	// 게시글 수정 - update 처리
	public int update(BoardVO board);

	// 문제1) 게시글 삭제 개수
	public int deleteNum();

	// 문제2) 작성자의 총 작성글 개수
	public int writeNum(String writer);


}
