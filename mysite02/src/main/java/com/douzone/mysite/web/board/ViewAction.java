package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.boardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		int hit = Integer.parseInt(request.getParameter("hit"));
		
		BoardVo vo = new boardRepository().findById(no);
		vo.setHit(hit+1);
		new boardRepository().updateHit(vo);
		
		request.setAttribute("vo", vo);
		MvcUtils.forward("board/view", request, response);
	}

}