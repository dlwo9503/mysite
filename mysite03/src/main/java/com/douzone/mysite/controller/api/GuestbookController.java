package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller("guestbookControllerApi")
@RequestMapping("/guestbook/api")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping("/add")
	public JsonResult add(@RequestBody GuestbookVo vo) {
		guestbookService.addMessage(vo);
		return JsonResult.success(vo);
	}
	
	@ResponseBody
	@RequestMapping("/delete/{no}")
	public JsonResult delete(@PathVariable("no") Long no,
			@RequestParam(value="password", required=true, defaultValue="")String password) {
		// 삭제 작업(GuestbookService)
		Long data = 0L;
		boolean result = guestbookService.deleteMessage(no, password);
		if(result == false) { // 삭제 실패
			data = -1L;
		} else { // 삭제 성공
			data = no;
		}
		return JsonResult.success(data);
	}
	
	@ResponseBody
	@RequestMapping("/list/{no}")
	public JsonResult list(@PathVariable("no") Long no) {
			List<GuestbookVo> vo = guestbookService.getMessageList(no);
			return JsonResult.success(vo);
	}
}
