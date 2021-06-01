package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.boardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		Long userNo = Long.parseLong(request.getParameter("userNo")); // 타입변환
		int depth = Integer.parseInt(request.getParameter("depth"));
		int order_no = Integer.parseInt(request.getParameter("order_no"));
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUserNo(userNo);
		vo.setDepth(depth);
		vo.setOrder_no(order_no+1);
		
		if(depth == 0) {
			new boardRepository().insert(vo);
			System.out.println(1);
		} else {
			new boardRepository().update(vo);
			new boardRepository().insert2(vo);
			System.out.println(2);
		}
		MvcUtils.redirect(request.getContextPath() + "/board?a=list", request, response);
	}
}
