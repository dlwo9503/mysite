package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = 0L;
		String no2 = request.getParameter("no2");
		if(request.getParameter("no") != null) {
			no = Long.parseLong(request.getParameter("no"));
		}
		if(no2 == null) {
			request.setAttribute("no2", "write");
		} else if (no2.equals("comment")) {
			request.setAttribute("no", no);
			request.setAttribute("no2", no2);
		}
		MvcUtils.forward("board/write", request, response);
	}

}