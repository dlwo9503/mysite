package com.douzone.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Controller
public class MainController {
	
	@Autowired
	ServletContext application; // application scope 사용
	
	@Autowired
	private SiteService siteService;
	
	@RequestMapping("")
	public String main(Model model) {
		SiteVo siteVo = siteService.findAll();
		model.addAttribute("siteVo", siteVo);
		application.setAttribute("title", siteVo.getTitle()); // application scope 사용, 서버가 돌아가는 동안 값이 유지되고 어디서든 사용 가능
		
		return "main/index";
		
	}
}
