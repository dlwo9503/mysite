package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.boardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userNo = request.getParameter("userNo");
		
		if(userNo != null) { // 댓글
			MvcUtils.forward("board/write", request, response);
		} else {
			userNo = "0";
			Long no = Long.parseLong(request.getParameter("no")); // 타입변환
			BoardVo list = new boardRepository().findAll2(no);
			request.setAttribute("list", list); // (이름, 데이터)
			MvcUtils.forward("board/write", request, response);
		}

	}

}
