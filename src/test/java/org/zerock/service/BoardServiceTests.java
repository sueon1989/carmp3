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

@RunWith(SpringJUnit4ClassRunner.class)	// 현재 테스트 코드가 스프링을 실행하는 역할을 할 것
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	@Autowired
	private BoardService service;
	
	@Test
	public void testExist() {
		
		// BoardService 객체가 제대로 주입이 가능한지 확인
		log.info("서비스 "+service);
		assertNotNull(service);
		
//		INFO : org.zerock.service.BoardServiceTests - 
//			서비스 org.apache.ibatis.binding.MapperProxy@3ad2e17
//		INFO : org.springframework.context.support.GenericApplicationContext - 
//			Closing org.springframework.context.support.GenericApplicationContext@6ddf90b0: 
//			startup date [Wed Dec 09 16:21:34 KST 2020]; root of context hierarchy
	}

//	목록(리스트)
	@Test
	public void testGetList() {
		
		List<BoardVO> boardList = service.getList();
		for(BoardVO board: boardList) {
			log.info(board);
		}
		
		// 람다식으로 표현 (교재 방법)
//		service.getList().forEach(board -> log.info(board));
		
	}
	
//	등록 - insert 처리 (SelectKey 사용)
	@Test
	public void testRegister() {
		
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 Register");
		board.setContent("새로 작성하는 내용 Register");
		board.setWriter("newbie");

		service.register(board);
		
		log.info("생성된 게시글의 번호: "+board.getBno());
		
//		INFO : jdbc.sqltiming - INSERT INTO tbl_board (bno, title, content, writer) 
//			VALUES (15, '새로 작성하는 글 Register', '새로 작성하는 내용 Register', 'newbie')
//		INFO : org.zerock.service.BoardServiceTests - 생성된 게시글의 번호: 15
	}
	
	
//	조회 (한 행) - read (select) 처리
	@Test
	public void testGet() {
		
		// 존재하는 게시물 번호로 테스트
		log.info("조회 결과: "+ service.get(1L) );	// bno: int가 아니라 long 
		
//		INFO : org.zerock.service.BoardServiceImpl - 조회......1
//		INFO : org.zerock.service.BoardServiceTests - 조회 결과: 
//			BoardVO(bno=1, title=테스트 제목, content=테스트 내용, writer=user00, 
//			regdate=Wed Dec 09 10:52:21 KST 2020, updateDate=Wed Dec 09 10:52:21 KST 2020)

	}
	
//	삭제 - delete 처리
	@Test
	public void testDelete() {
		
		// 존재하는 게시물 번호로 테스트
		log.info("삭제 DELETE 결과: "+ service.remove(4L));
		
//		INFO : org.zerock.service.BoardServiceImpl - 삭제......4
//		INFO : org.zerock.service.BoardServiceTests - 삭제 DELETE 결과: true
	}
	
//	수정 - update 처리
	@Test
	public void testUpdate() {
		
		// 특정한 게시물을 먼저 조회
		BoardVO board = service.get(1L);
		
		// 존재하는 게시물 번호로 테스트
		board.setBno(5L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("user00");
		
		log.info("수정 UPDATE 결과: "+ service.modify(board));
		log.info(board);
		
//		INFO : org.zerock.service.BoardServiceTests - 수정 UPDATE 결과: true
//		INFO : org.zerock.service.BoardServiceTests - BoardVO(bno=5, title=수정된 제목, 
//				content=수정된 내용, writer=user00, regdate=Wed Dec 09 10:52:21 KST 2020, 
//				updateDate=Wed Dec 09 10:52:21 KST 2020)
	}
	
	
	
//	게시글 삭제 개수
	@Test
	public void testRemoveCount() {
		
		// 존재하는 게시물 번호로 테스트
		log.info("게시글 삭제 개수: "+ service.removeCount() );
		
//		INFO : org.zerock.service.BoardServiceImpl - 게시글 삭제 개수......4
//		INFO : org.zerock.service.BoardServiceTests - 게시글 삭제 개수: 4

	}
	
//	게시글 삭제 개수
	@Test
	public void testWriteCount() {
		
		// 존재하는 작성자 이름으로 테스트
		String writer = "newbie";
		log.info(writer+"의 총 작성글 개수: "+ service.writeCount(writer));

//		INFO : org.zerock.service.BoardServiceImpl - newbie의 총 작성글 개수: 5
//		INFO : org.zerock.service.BoardServiceTests - newbie의 총 작성글 개수: 5

	}
	
	
	
	// 람다식 스터디
	@Test
	public void addTest() {
		int a=3;
		int b=4;
		
		// 함수 호출
		add(3,4);
//		INFO : org.zerock.service.BoardServiceTests - 더한 값: 7
		
		// 람다식으로 구현
//		(a,b) -> log.info("더한 값: "+ (a+b));
//
//		board -> log.info(board);
//
//		service.getList().forEach(board -> log.info(board));
		
	}
	
	public void add(int a, int b) {
		log.info("더한 값: "+ (a+b));
	}
	
	
}
