package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller	// 스프링의 빈으로 인식
@Log4j		
@RequestMapping("/board/*")	// /board로 시작하는 모든 처리를 BoardController가 처리하도록 지정
@AllArgsConstructor			// BoardController는 BoardService에 의존적: 생성자를 만들고 자동으로 주입하도록 함
public class BoardController {

		private BoardService service;	
		
//		http://localhost:8082/board/list
//			* Console에서 로그를 확인하기 위해 
//			## log4j.xml에서 warn -> info로 변경
//				37줄: <priority value="info" />	<!-- 기존: warn -->
		// 목록(리스트)
		@GetMapping("/list")
		public void list(Model model) {
			
			log.info("리스트");
			model.addAttribute("list", service.getList());
//			model.addAttribute(service.getList());	// 이름 생략해도 리턴타입 list로 보내짐
		}	
		
		// @GetMapping("/list")
		// public void list(Criteria cri, Model model) {
		//
		// log.info("list: " + cri);
		// model.addAttribute("list", service.getList(cri));
		//
		// }

//		@GetMapping("/list")
//		public void list(Criteria cri, Model model) {
//
//			log.info("list: " + cri);
//			model.addAttribute("list", service.getList(cri));
//			// model.addAttribute("pageMaker", new PageDTO(cri, 123));
//
//			int total = service.getTotal(cri);
//
//			log.info("total: " + total);
//
//			model.addAttribute("pageMaker", new PageDTO(cri, total));
//
//		}

		// p238
		// 등록화면: Get방식으로 입력 페이지를 볼 수 있도록
		@GetMapping("/register")
		public void register() {

		}

		// 등록2 (교재방법)
//		@PostMapping("/register")
//		public String register(BoardVO board, RedirectAttributes rttr) {
//
//			// 등록 작업
//			log.info("등록: " + board);
//			service.register(board);
//
//			// 새롭게 등록된 게시물의 번호를 같이 전달
//			rttr.addFlashAttribute("result", board.getBno());
//
//			// 리타이렉트 방식으로 목록화면 board/list 호출 
//			return "redirect:/board/list";
//		}

		// 등록1 (기존방법) - model 사용
		@PostMapping("/register")
		public String register(BoardVO board, Model model) {

			// 등록 작업
			log.info("등록: " + board);
			service.register(board);

			// 새롭게 등록된 게시물의 번호를 같이 전달
			model.addAttribute("result", board.getBno());
			// 나중에 한번만 보낼 수 있도록 변경하자!!!

			// 리타이렉트 방식으로 목록화면 board/list 호출 
			return "redirect:/board/list";
		}

		// 조회 (한 행)
		@GetMapping("/get")
		public void get(/* @RequestParam("bno") */ Long bno, Model model) {
		
			log.info("조회: /get");
			
			// 화면 쪽으로 해당 번호의 게시물을 전달
			model.addAttribute("board", service.get(bno));
//			model.addAttribute(service.get(bno));	// 이름 생략하면 리턴타입 BoardVO로 보내짐
		}
		
//		@GetMapping({ "/get", "/modify" })
//		public void get(@RequestParam("bno") Long bno, Model model) {
//		
//			log.info("/get or modify ");
//			model.addAttribute("board", service.get(bno));
//		}

//		@GetMapping({ "/get", "/modify" })
//		public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
//
//			log.info("/get or modify");
//			model.addAttribute("board", service.get(bno));
//		}
//
		// 수정
		@PostMapping("/modify")
		public String modify(BoardVO board, RedirectAttributes rttr) {
			
			log.info("수정:" + board);
			
			if (service.modify(board)) {					// 글 수정에 성공한 경우에만
				rttr.addFlashAttribute("result", "성공");	// RedirectAttributes에 추가
			}
			return "redirect:/board/list";
		}
//
//		@PostMapping("/modify")
//		public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
//			log.info("modify:" + board);
//
//			if (service.modify(board)) {
//				rttr.addFlashAttribute("result", "success");
//			}
//
//			rttr.addAttribute("pageNum", cri.getPageNum());
//			rttr.addAttribute("amount", cri.getAmount());
//			rttr.addAttribute("type", cri.getType());
//			rttr.addAttribute("keyword", cri.getKeyword());
//
//			return "redirect:/board/list";
//		}

		// 삭제
		@PostMapping("/remove")
		public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr)
		{
			
			log.info("remove..." + bno);
			if (service.remove(bno)) {						// 글 삭제에 성공한 경우에만
				rttr.addFlashAttribute("result", "success");	// RedirectAttributes에 추가
			}
			return "redirect:/board/list";
		}
		
		
//		@PostMapping("/remove")
//		public String remove(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr) {
//
//			log.info("remove..." + bno);
//			if (service.remove(bno)) {
//				rttr.addFlashAttribute("result", "success");
//			}
//			rttr.addAttribute("pageNum", cri.getPageNum());
//			rttr.addAttribute("amount", cri.getAmount());
//			rttr.addAttribute("type", cri.getType());
//			rttr.addAttribute("keyword", cri.getKeyword());
//
//			return "redirect:/board/list";
//		}
}
