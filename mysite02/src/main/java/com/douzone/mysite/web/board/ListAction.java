package com.douzone.mysite.web.board;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		
		List<BoardVo> list = new boardRepository().findAll();
		
		Date date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String regDate = transFormat.format(date);
		// 2. request범위에 데이터(객체) 저장, 여기에 list를 저장해서 jsp쪽으로 넘겨줄거임
		request.setAttribute("list", list); // (이름, 데이터)
		request.setAttribute("regDate", regDate); // (이름, 데이터)
		
		MvcUtils.forward("board/list", request, response);
	}

}
