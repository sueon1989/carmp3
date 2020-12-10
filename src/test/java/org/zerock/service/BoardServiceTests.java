package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)	// ���� �׽�Ʈ �ڵ尡 �������� �����ϴ� ������ �� ��
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	@Autowired
	private BoardService service;
	
	@Test
	public void testExist() {
		
		// BoardService ��ü�� ����� ������ �������� Ȯ��
		log.info("���� "+service);
		assertNotNull(service);
		
//		INFO : org.zerock.service.BoardServiceTests - 
//			���� org.apache.ibatis.binding.MapperProxy@3ad2e17
//		INFO : org.springframework.context.support.GenericApplicationContext - 
//			Closing org.springframework.context.support.GenericApplicationContext@6ddf90b0: 
//			startup date [Wed Dec 09 16:21:34 KST 2020]; root of context hierarchy
	}

//	���(����Ʈ)
	@Test
	public void testGetList() {
		
		List<BoardVO> boardList = service.getList();
		for(BoardVO board: boardList) {
			log.info(board);
		}
		
		// ���ٽ����� ǥ�� (���� ���)
//		service.getList().forEach(board -> log.info(board));
		
	}
	
//	��� - insert ó�� (SelectKey ���)
	@Test
	public void testRegister() {
		
		BoardVO board = new BoardVO();
		board.setTitle("���� �ۼ��ϴ� �� Register");
		board.setContent("���� �ۼ��ϴ� ���� Register");
		board.setWriter("newbie");

		service.register(board);
		
		log.info("������ �Խñ��� ��ȣ: "+board.getBno());
		
//		INFO : jdbc.sqltiming - INSERT INTO tbl_board (bno, title, content, writer) 
//			VALUES (15, '���� �ۼ��ϴ� �� Register', '���� �ۼ��ϴ� ���� Register', 'newbie')
//		INFO : org.zerock.service.BoardServiceTests - ������ �Խñ��� ��ȣ: 15
	}
	
	
//	��ȸ (�� ��) - read (select) ó��
	@Test
	public void testGet() {
		
		// �����ϴ� �Խù� ��ȣ�� �׽�Ʈ
		log.info("��ȸ ���: "+ service.get(1L) );	// bno: int�� �ƴ϶� long 
		
//		INFO : org.zerock.service.BoardServiceImpl - ��ȸ......1
//		INFO : org.zerock.service.BoardServiceTests - ��ȸ ���: 
//			BoardVO(bno=1, title=�׽�Ʈ ����, content=�׽�Ʈ ����, writer=user00, 
//			regdate=Wed Dec 09 10:52:21 KST 2020, updateDate=Wed Dec 09 10:52:21 KST 2020)

	}
	
//	���� - delete ó��
	@Test
	public void testDelete() {
		
		// �����ϴ� �Խù� ��ȣ�� �׽�Ʈ
		log.info("���� DELETE ���: "+ service.remove(4L));
		
//		INFO : org.zerock.service.BoardServiceImpl - ����......4
//		INFO : org.zerock.service.BoardServiceTests - ���� DELETE ���: true
	}
	
//	���� - update ó��
	@Test
	public void testUpdate() {
		
		// Ư���� �Խù��� ���� ��ȸ
		BoardVO board = service.get(1L);
		
		// �����ϴ� �Խù� ��ȣ�� �׽�Ʈ
		board.setBno(5L);
		board.setTitle("������ ����");
		board.setContent("������ ����");
		board.setWriter("user00");
		
		log.info("���� UPDATE ���: "+ service.modify(board));
		log.info(board);
		
//		INFO : org.zerock.service.BoardServiceTests - ���� UPDATE ���: true
//		INFO : org.zerock.service.BoardServiceTests - BoardVO(bno=5, title=������ ����, 
//				content=������ ����, writer=user00, regdate=Wed Dec 09 10:52:21 KST 2020, 
//				updateDate=Wed Dec 09 10:52:21 KST 2020)
	}
	
	
	
//	�Խñ� ���� ����
	@Test
	public void testRemoveCount() {
		
		// �����ϴ� �Խù� ��ȣ�� �׽�Ʈ
		log.info("�Խñ� ���� ����: "+ service.removeCount() );
		
//		INFO : org.zerock.service.BoardServiceImpl - �Խñ� ���� ����......4
//		INFO : org.zerock.service.BoardServiceTests - �Խñ� ���� ����: 4

	}
	
//	�Խñ� ���� ����
	@Test
	public void testWriteCount() {
		
		// �����ϴ� �ۼ��� �̸����� �׽�Ʈ
		String writer = "newbie";
		log.info(writer+"�� �� �ۼ��� ����: "+ service.writeCount(writer));

//		INFO : org.zerock.service.BoardServiceImpl - newbie�� �� �ۼ��� ����: 5
//		INFO : org.zerock.service.BoardServiceTests - newbie�� �� �ۼ��� ����: 5

	}
	
	
	
	// ���ٽ� ���͵�
	@Test
	public void addTest() {
		int a=3;
		int b=4;
		
		// �Լ� ȣ��
		add(3,4);
//		INFO : org.zerock.service.BoardServiceTests - ���� ��: 7
		
		// ���ٽ����� ����
//		(a,b) -> log.info("���� ��: "+ (a+b));
//
//		board -> log.info(board);
//
//		service.getList().forEach(board -> log.info(board));
		
	}
	
	public void add(int a, int b) {
		log.info("���� ��: "+ (a+b));
	}
	
	
}
