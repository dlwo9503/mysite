package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Auth(role="ADMIN") // admin으로 로그인 했을 때만
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private SiteService siteService;
	
	@RequestMapping("")
	public String main(Model model) {
		SiteVo siteVo = siteService.findAll();
		model.addAttribute(siteVo);
		return "admin/main";
	}
	
	@RequestMapping(value="/main/update", method=RequestMethod.POST)
//	public String updateMain(SiteVo vo) {
	public String updateMain(SiteVo siteVo) {
		siteService.updateMain(siteVo);
		return "redirect:/admin";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
}
