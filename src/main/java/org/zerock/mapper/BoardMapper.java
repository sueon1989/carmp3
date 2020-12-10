package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;

public interface BoardMapper {
	
	// �Խñ� ���(����Ʈ)
//	@Select("select * from tbl_board where bno>0")
	public List<BoardVO> getList();
	
	// �Խñ� ��� - insert ó��
	public void insert(BoardVO board);

	// �Խñ� ���2 - @SelectKey ��� (�ڵ����� �߰��Ǵ� PK �� Ȯ��)
	public void insertSelectKey(BoardVO board);
	
	// �Խñ� ��ȸ (�� ��) - read (select) ó��
	public BoardVO read(Long bno);	// ��ü Ÿ������ �־��־�� �� (long�� �ƴ� Long)
	
	// �Խñ� ���� - delete ó��
	public int delete(Long bno);

	// �Խñ� ���� - update ó��
	public int update(BoardVO board);

	// ����1) �Խñ� ���� ����
	public int deleteNum();

	// ����2) �ۼ����� �� �ۼ��� ����
	public int writeNum(String writer);


}
