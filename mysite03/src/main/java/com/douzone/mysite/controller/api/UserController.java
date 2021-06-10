package com.douzone.mysite.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@RestController("userControllerApi") // 다른 패키지의 유저 컨트롤러와 중복을 피하기위해서 id를 지정해줌
@RequestMapping("/user/api")
public class UserController {
	@Autowired
	private UserService userService;
	
//	@RequestMapping("/checkemail")
	@GetMapping("/checkemail") // GetMapping를 사용하면 뒤에 따로 GET를 써주지 않아도 됨
	public Object checkemail(
			@RequestParam(value="email", required=true, defaultValue="") String email) {
		System.out.println("------->" + email);
		
		UserVo userVo = userService.getUser(email);
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success"); // 통신이 성공했냐 실패했냐 -> 통신 여부
		result.put("exist", userVo != null); // 데이터가 존재하냐 안하냐 -> 데이터 존재 여부
		
		return result;
	}
}
