package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String guestbook(Model model) {
//		List<GuestbookVo> list = guestbookRepository.findAll();
		List<GuestbookVo> list = boardService.getMessageList();
		
		model.addAttribute("list", list);
		return "board/list";
	}
}
