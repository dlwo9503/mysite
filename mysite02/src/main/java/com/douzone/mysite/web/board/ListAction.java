package com.douzone.mysite.web.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.boardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 0;
		int count = 0;
		List<BoardVo>list = null;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		if(request.getParameter("kwd") != null) {
			String keyword = request.getParameter("kwd");
			count = new boardRepository().findcount(keyword);
			list = new boardRepository().findAll2(page, keyword);
		} else {
			count = new boardRepository().count();
			list = new boardRepository().findAll(page);
		}
		
		int firstpage = 0;
		int lastpage = (int) Math.ceil(count/5);
		int size = list.size();
		
		request.setAttribute("firstPage", firstpage);
		request.setAttribute("lastPage", lastpage);
		request.setAttribute("size", size);
		request.setAttribute("list", list);
		MvcUtils.forward("board/list", request, response);
	}

}