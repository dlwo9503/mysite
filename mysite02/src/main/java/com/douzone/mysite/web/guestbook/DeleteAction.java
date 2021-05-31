package com.douzone.mysite.web.guestbook;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String no2 = request.getParameter("no");
		Long no = Long.parseLong(no2);
		String pw = request.getParameter("password");
		
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(no);
		vo.setPassword(pw);
		
		GuestbookVo vo2 = new GuestbookRepository().findAll2(no);
		String pw2 = vo2.getPassword();
		
		if(pw.equals(pw2)){
			new GuestbookRepository().delete(vo);
			// redirect 응답
			response.sendRedirect(request.getContextPath() + "/guestbook");	
		} else {
			// 경고창
			response.setContentType("text/html; charset=UTF-8"); // 서블릿에서 html 사용
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('비밀번호가 틀렸습니다.'); location.href='guestbook'</script>"); 
			writer.close();
		}
	}

}
