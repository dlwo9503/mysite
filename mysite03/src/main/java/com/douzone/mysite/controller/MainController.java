package com.douzone.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.mysite.vo.UserVo;

@Controller
public class MainController {
	
	@Autowired
	private ServletContext application; // application scope 사용
	
	@Autowired
	private SiteService siteService;
	
	@RequestMapping("")
	public String main(Model model) {
		SiteVo siteVo = siteService.findAll();
		model.addAttribute("siteVo", siteVo);
		application.setAttribute("title", siteVo.getTitle()); // application scope 사용, 서버가 돌아가는 동안 값이 유지되고 어디서든 사용 가능
		
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/msg1")
	public String message1() {
		return "안녕하세요";
	}
	
	@ResponseBody
	@RequestMapping("/msg2")
	public Object message2() {
		UserVo vo = new UserVo();
		vo.setNo(1L);
		vo.setEmail("dlwo9503@naver.com");
		vo.setName("이재성");
		return vo;
	}
}
