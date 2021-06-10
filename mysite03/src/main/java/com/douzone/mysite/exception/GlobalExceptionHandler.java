package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 이게 AOP 역할을 함 
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);
	@ExceptionHandler(Exception.class) // 모든 예외를 받음
	public void handlerException(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Exception e) throws Exception{ // e에 대한 내용을 받음, 모델도 받을 수 있음
		// 1. 로깅(logging)
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors)); // 스트림으로 넘김
		LOGGER.error(errors.toString());
		// 2. 요청 구분
		// 만약, JSON 요청인 경우이면 request header의 Accept에 application/json 
		// 만약, HTML 요청인 경우이면 request header의 Accept에 text/html
		String accept = request.getHeader("accept");
		
		if(accept.matches(".*application/json.*")) {// .*은 모든 문자 포함한다는 뜻
			// 3. json 응답
		} else {
			// 3. 사과 페이지 가기(정상종료)
			request.setAttribute("exception", errors.toString()); // 모델에 exception이라는 키와 값 저장해서 보냄
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		}
	}
}
