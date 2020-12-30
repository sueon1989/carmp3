package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)	// ���� �׽�Ʈ �ڵ尡 �������� �����ϴ� ������ �� ��
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper mapper;
	
	// �Խñ� ���(����Ʈ)
	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}
	
	// �Խñ� ���(����Ʈ)
	@Test
	public void testGetListWithPaging() {
		Criteria cri = new Criteria(2, 20);
		mapper.getListWithPaging(cri).forEach(board -> log.info(board));
	}
	
	// member ���(����Ʈ)
	@Test
	public void testGetList2() {
		mapper.getList2().forEach(member -> log.info(member));
	}
	
	// �Խñ� ���1 - insert ó��
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("���� �ۼ��ϴ� ��");
		board.setContent("���� �ۼ��ϴ� ����");
		board.setWriter("newbie");
		
		mapper.insert(board);
		
		log.info(board);
//		BoardVO(bno=null, title=���� �ۼ��ϴ� ��, content=���� �ۼ��ϴ� ����, 
//		writer=newbie, regdate=null, updateDate=null)
	}
	
	// �Խñ� ���2 - @SelectKey ��� (�ڵ����� �߰��Ǵ� PK �� Ȯ��)
	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("���� �ۼ��ϴ� �� Select Key");
		board.setContent("���� �ۼ��ϴ� ���� Select Key");
		board.setWriter("newbie");

		log.info("��� ����: "+board);

		mapper.insertSelectKey(board);
		
		log.info(board);
//		BoardVO(bno=12, title=���� �ۼ��ϴ� �� Select Key, content=���� �ۼ��ϴ� ���� Select Key, 
//		writer=newbie, regdate=null, updateDate=null)
	}
	
	// �Խñ� ��ȸ (�� ��) - read (select) ó��
	@Test
	public void testRead() {
		
		// �����ϴ� �Խù� ��ȣ�� �׽�Ʈ
		BoardVO board = mapper.read(5L);	// bno: int�� �ƴ϶� long 
		
		log.info(board);
	}
	
	// �Խñ� ���� - delete ó��
	@Test
	public void testDelete() {
		
		log.info("���� �׽�Ʈ DELETE COUNT: "+ mapper.delete(3L));
	}
	
	// �Խñ� ���� - update ó��
	@Test
	public void testUpdate() {
		
		BoardVO board = new BoardVO();
		
		// �����ϴ� �Խù� ��ȣ�� �׽�Ʈ
		board.setBno(8L);
		board.setTitle("������ ����");
		board.setContent("������ ����");
		board.setWriter("user00");
		
		log.info("���� �׽�Ʈ UPDATE COUNT: "+ mapper.update(board));
		log.info(board);
	}
	
	@Test
	public void testPaging() {

		Criteria cri = new Criteria();
	    cri.setPageNum(1);	// 3������ 
	    cri.setAmount(10);	// 10����

		List<BoardVO> list = mapper.getListWithPaging(cri);

		list.forEach(board -> log.info(board));

	}
	
//	  @Test
//	  public void testSearch() {
//
//	    Criteria cri = new Criteria();
//	    cri.setKeyword("Ű����");
//	    cri.setType("TCW");
//
//	    List<BoardVO> list = mapper.getListWithPaging(cri);
//
//	    list.forEach(board -> log.info(board));
//	  }

	
	
	
	
	
	
	
	
	
	
	
	
	// �Խñ� ���� ����	
	@Test
	public void testDeleteNum() {
		
		// �����ϴ� �Խù� ��ȣ�� �׽�Ʈ
		log.info("�Խñ� ���� ����: "+ mapper.deleteNum());
	}
	
	
	// �ۼ����� �� �ۼ��� ����
	@Test
	public void testWriteNum() {

		String writer = "newbie";
		
		// �����ϴ� �ۼ��� �̸����� �׽�Ʈ 
		log.info(writer +"�� �� �ۼ��� ����: "+ mapper.writeNum(writer));
	}
}
