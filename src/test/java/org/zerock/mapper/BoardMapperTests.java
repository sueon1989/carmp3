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

@RunWith(SpringJUnit4ClassRunner.class)	// 현재 테스트 코드가 스프링을 실행하는 역할을 할 것
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper mapper;
	
	// 게시글 목록(리스트)
	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}
	
	// 게시글 목록(리스트)
	@Test
	public void testGetListWithPaging() {
		Criteria cri = new Criteria(2, 20);
		mapper.getListWithPaging(cri).forEach(board -> log.info(board));
	}
	
	// member 목록(리스트)
	@Test
	public void testGetList2() {
		mapper.getList2().forEach(member -> log.info(member));
	}
	
	// 게시글 등록1 - insert 처리
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		mapper.insert(board);
		
		log.info(board);
//		BoardVO(bno=null, title=새로 작성하는 글, content=새로 작성하는 내용, 
//		writer=newbie, regdate=null, updateDate=null)
	}
	
	// 게시글 등록2 - @SelectKey 사용 (자동으로 추가되는 PK 값 확인)
	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 Select Key");
		board.setContent("새로 작성하는 내용 Select Key");
		board.setWriter("newbie");

		log.info("등록 이전: "+board);

		mapper.insertSelectKey(board);
		
		log.info(board);
//		BoardVO(bno=12, title=새로 작성하는 글 Select Key, content=새로 작성하는 내용 Select Key, 
//		writer=newbie, regdate=null, updateDate=null)
	}
	
	// 게시글 조회 (한 행) - read (select) 처리
	@Test
	public void testRead() {
		
		// 존재하는 게시물 번호로 테스트
		BoardVO board = mapper.read(5L);	// bno: int가 아니라 long 
		
		log.info(board);
	}
	
	// 게시글 삭제 - delete 처리
	@Test
	public void testDelete() {
		
		log.info("삭제 테스트 DELETE COUNT: "+ mapper.delete(3L));
	}
	
	// 게시글 수정 - update 처리
	@Test
	public void testUpdate() {
		
		BoardVO board = new BoardVO();
		
		// 존재하는 게시물 번호로 테스트
		board.setBno(8L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("user00");
		
		log.info("수정 테스트 UPDATE COUNT: "+ mapper.update(board));
		log.info(board);
	}
	
	@Test
	public void testPaging() {

		Criteria cri = new Criteria();
	    cri.setPageNum(1);	// 3페이지 
	    cri.setAmount(10);	// 10개씩

		List<BoardVO> list = mapper.getListWithPaging(cri);

		list.forEach(board -> log.info(board));

	}
	
//	  @Test
//	  public void testSearch() {
//
//	    Criteria cri = new Criteria();
//	    cri.setKeyword("키워드");
//	    cri.setType("TCW");
//
//	    List<BoardVO> list = mapper.getListWithPaging(cri);
//
//	    list.forEach(board -> log.info(board));
//	  }

	
	
	
	
	
	
	
	
	
	
	
	
	// 게시글 삭제 개수	
	@Test
	public void testDeleteNum() {
		
		// 존재하는 게시물 번호로 테스트
		log.info("게시글 삭제 개수: "+ mapper.deleteNum());
	}
	
	
	// 작성자의 총 작성글 개수
	@Test
	public void testWriteNum() {

		String writer = "newbie";
		
		// 존재하는 작성자 이름으로 테스트 
		log.info(writer +"의 총 작성글 개수: "+ mapper.writeNum(writer));
	}
}
