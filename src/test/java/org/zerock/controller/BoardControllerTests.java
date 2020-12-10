package org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)	// 현재 테스트 코드가 스프링을 실행하는 역할을 할 것
@WebAppConfiguration	// WebApplicationContext를 이용하기 위해
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })	// ServletContext를 사용하기 위해
@Log4j
public class BoardControllerTests {

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;	// 가짜 MVC  (URL, 파라미터 테스트 용도)
	// URL과 파라미터 등을 브라우저에서 사용하는 것처럼 가짜로 만들어서 Controller를 실행
	
	
	@Before	// 모든 테스트 전에 매번 실행되는 메서드
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	// 여기까지는 항상 똑같음
	
	
	
	
	// 목록(리스트)
	@Test
	public void testList() throws Exception {

		log.info( mockMvc
			.perform(MockMvcRequestBuilders.get("/board/list"))	// GET 방식으로 호출
			.andReturn()		// 반환된 결과를 이용해서
			.getModelAndView()	// Model에 어떤 데이터들이 담겨 있는지 확인
			.getModelMap());
	}

	// 등록
	@Test
	public void testRegister() throws Exception {

		String resultPage = mockMvc
			.perform(MockMvcRequestBuilders.post("/board/register")	// POST 방식으로 호출
			.param("title", "테스트 새글 제목")			// 전달해야 하는 파라미터들을 지정
			.param("content", "테스트 새글 내용")
			.param("writer", "user00"))
			.andReturn().getModelAndView().getViewName();

		log.info(resultPage);
	}

	// 조회 (한 행)
	@Test
	public void tetGet() throws Exception {

		log.info(mockMvc
				.perform(MockMvcRequestBuilders.get("/board/get")
				.param("bno", "2"))
				.andReturn().getModelAndView().getModelMap());
	}
	
	// 수정 
	@Test
	public void testModify() throws Exception {

		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno", "1")
				.param("title", "수정된 테스트 새글 제목")
				.param("content", "수정된 테스트 새글 내용")
				.param("writer", "user00"))
				.andReturn().getModelAndView().getViewName();

		log.info(resultPage);
	}

	// 삭제
	@Test
	public void testRemove() throws Exception {
		// 삭제전 데이터베이스에 게시물 번호 확인할 것
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.post("/board/remove")
						.param("bno", "16"))
				.andReturn().getModelAndView().getViewName();

		log.info(resultPage);
	}
	
	// 삭제
//	@Test
//	public void testListPaging() throws Exception {
//
//		log.info(mockMvc.perform(
//				MockMvcRequestBuilders.get("/board/list")
//				.param("pageNum", "2")
//				.param("amount", "50"))
//				.andReturn().getModelAndView().getModelMap());
//	}
}
