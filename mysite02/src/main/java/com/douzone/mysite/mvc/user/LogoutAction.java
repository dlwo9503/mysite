package com.douzone.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.Action;
import com.douzone.mvc.util.MVCUtils;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session != null) { // 로그인 안하고 로그아웃 페이지에 들어가는 경우 대비
			// 로그아웃
			session.removeAttribute("authUser");
			session.invalidate();
			
			MVCUtils.redirect(request.getContextPath(), request, response);
		}
		
	}

}
