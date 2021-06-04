package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 이게 AOP 역할을 함 
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class) // 모든 예외를 받음
	public String handlerException(Model model, Exception e) { // e에 대한 내용을 받음, 모델도 받을 수 있음
		// 1. 로깅(logging)
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors)); // 스트림으로 넘김
		
		System.out.println(errors); // 에러 출력
		// 2. 사과 페이지
		// 3. 정상 종료
		
		model.addAttribute("exception", errors.toString()); // 모델에 exception이라는 키와 값 저장해서 보냄
		return "error/exception"; // 이쪽으로 보냄
	}
}
