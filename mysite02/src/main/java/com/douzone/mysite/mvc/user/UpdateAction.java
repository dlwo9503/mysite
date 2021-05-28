package com.douzone.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.Action;
import com.douzone.mvc.util.MVCUtils;
import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		Long no = Long.parseLong(request.getParameter("no")); // 타입변환
		
		UserVo vo = new UserVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setGender(gender);
		vo.setNo(no);
		
		new UserRepository().update(vo);
		
		MVCUtils.redirect(request.getContextPath(), request, response);
		
	}
}
