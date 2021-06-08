package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 이게 AOP 역할을 함 
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);
	@ExceptionHandler(Exception.class) // 모든 예외를 받음
	public String handlerException(Model model, Exception e) { // e에 대한 내용을 받음, 모델도 받을 수 있음
		// 1. 로깅(logging)
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors)); // 스트림으로 넘김
		
//		System.out.println(errors); // 에러 출력
		/**
		 * 1. appender
		 * 		file appender /log-mysite/exception.log
		 * 		file appender /log-mysite/exception.1.log.zip
		 * 		10M (아카이빙 정책)
		 * 		1-10(rolling)
		 * 		console appender
		 * 
		 * 2. logger - com.douzone.mysite.exception, level(error), (console+file) appender
		 * 	  logger - Root, level(debug), console appender
		 * 
		 */
		
		LOGGER.error(errors.toString());
		// 2. 사과 페이지
		// 3. 정상 종료
		
		model.addAttribute("exception", errors.toString()); // 모델에 exception이라는 키와 값 저장해서 보냄
		return "error/exception"; // 이쪽으로 보냄
	}
}
