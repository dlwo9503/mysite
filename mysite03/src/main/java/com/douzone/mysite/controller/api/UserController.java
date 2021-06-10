package com.douzone.mysite.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("userControllerApi") // 다른 패키지의 유저 컨트롤러와 중복을 피하기위해서 id를 지정해줌
@RequestMapping("/user/api")
public class UserController {
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public Object checkemail(
			@RequestParam(value="email", required=true, defaultValue="") String email) {
		System.out.println("------->" + email);
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success"); // 통신이 성공했냐 실패했냐 -> 통신 여부
		result.put("exist", false); // 데이터가 존재하냐 안하냐 -> 데이터 존재 여부
		
		return result;
	}
}
