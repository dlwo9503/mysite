package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserVo vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value = "/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	// login 없앰
	
	// 인증을 하고 사용할 수 있도록 처리하는거, 로그인을 했냐 안했냐를 인증, role을 이용해서 권한을 줄 수 있음, Auth를 해석하는 놈은 다른곳에 있음
	
	// logout 없앰
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) { // 로그인 안했을 때, 접근제어
			return "redirect:/";
		}
		
		Long no = authUser.getNo();
		UserVo userVo = userService.getUser(no);
		
		model.addAttribute("user", userVo);
		return "user/update";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(HttpSession session, UserVo userVo) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) { // 로그인 안했을 때, 접근제어
			return "redirect:/";
		}
		
		userVo.setNo(authUser.getNo());
		userService.updateUser(userVo);
		authUser.setName(userVo.getName());
		
		return "redirect:/user/update";
	}
}
