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

@Controller	// �������� ������ �ν�
@Log4j		
@RequestMapping("/board/*")	// /board�� �����ϴ� ��� ó���� BoardController�� ó���ϵ��� ����
@AllArgsConstructor			// BoardController�� BoardService�� ������: �����ڸ� ����� �ڵ����� �����ϵ��� ��
public class BoardController {

		private BoardService service;	
		
//		http://localhost:8082/board/list
//			* Console���� �α׸� Ȯ���ϱ� ���� 
//			## log4j.xml���� warn -> info�� ����
//				37��: <priority value="info" />	<!-- ����: warn -->
		// ���(����Ʈ)
		@GetMapping("/list")
		public void list(Model model) {
			
			log.info("����Ʈ");
			model.addAttribute("list", service.getList());
//			model.addAttribute(service.getList());	// �̸� �����ص� ����Ÿ�� list�� ������
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
		// ���ȭ��: Get������� �Է� �������� �� �� �ֵ���
		@GetMapping("/register")
		public void register() {

		}

		// ���2 (������)
//		@PostMapping("/register")
//		public String register(BoardVO board, RedirectAttributes rttr) {
//
//			// ��� �۾�
//			log.info("���: " + board);
//			service.register(board);
//
//			// ���Ӱ� ��ϵ� �Խù��� ��ȣ�� ���� ����
//			rttr.addFlashAttribute("result", board.getBno());
//
//			// ��Ÿ�̷�Ʈ ������� ���ȭ�� board/list ȣ�� 
//			return "redirect:/board/list";
//		}

		// ���1 (�������) - model ���
		@PostMapping("/register")
		public String register(BoardVO board, Model model) {

			// ��� �۾�
			log.info("���: " + board);
			service.register(board);

			// ���Ӱ� ��ϵ� �Խù��� ��ȣ�� ���� ����
			model.addAttribute("result", board.getBno());
			// ���߿� �ѹ��� ���� �� �ֵ��� ��������!!!

			// ��Ÿ�̷�Ʈ ������� ���ȭ�� board/list ȣ�� 
			return "redirect:/board/list";
		}

		// ��ȸ (�� ��)
		@GetMapping("/get")
		public void get(/* @RequestParam("bno") */ Long bno, Model model) {
		
			log.info("��ȸ: /get");
			
			// ȭ�� ������ �ش� ��ȣ�� �Խù��� ����
			model.addAttribute("board", service.get(bno));
//			model.addAttribute(service.get(bno));	// �̸� �����ϸ� ����Ÿ�� BoardVO�� ������
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
		// ����
		@PostMapping("/modify")
		public String modify(BoardVO board, RedirectAttributes rttr) {
			
			log.info("����:" + board);
			
			if (service.modify(board)) {					// �� ������ ������ ��쿡��
				rttr.addFlashAttribute("result", "����");	// RedirectAttributes�� �߰�
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

		// ����
		@PostMapping("/remove")
		public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr)
		{
			
			log.info("remove..." + bno);
			if (service.remove(bno)) {						// �� ������ ������ ��쿡��
				rttr.addFlashAttribute("result", "success");	// RedirectAttributes�� �߰�
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
