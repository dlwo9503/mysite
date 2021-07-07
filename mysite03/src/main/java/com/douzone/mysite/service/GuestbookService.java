package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	GuestbookRepository guestbookRepository;

	public List<GuestbookVo> getMessageList() {
		return guestbookRepository.findAll();
	}
	
	public List<GuestbookVo> getMessageList(Long no/*기준*/) {
		return guestbookRepository.findAll(no);
	}
	
	public void deleteMessage(Long no, String password) {
//		GuestbookVo vo = new GuestbookVo();
//		vo.setNo(no);
//		vo.setPassword(password);
		
		GuestbookVo vo1 = guestbookRepository.findAll2(no);
		if(vo1.getPassword().equals(password)){
			guestbookRepository.delete(vo1);
		}
	}
	
	public void addMessage(GuestbookVo vo) {
		guestbookRepository.insert(vo);
	}

}
