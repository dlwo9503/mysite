package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class BoardService {
	@Autowired
	BoardRepository boardRepository;

	public List<GuestbookVo> getMessageList() {
		// TODO Auto-generated method stub
		return null;
	}
}
