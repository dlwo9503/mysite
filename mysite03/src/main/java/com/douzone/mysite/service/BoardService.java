package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	BoardRepository boardRepository;

	public int getfindcount(String keyword) {
		return boardRepository.findcount(keyword);
	}

	public List<BoardVo> getfindAll2(Integer page, String keyword) {
		return boardRepository.findAll2(page, keyword);
	}

	public int getcount() {
		return boardRepository.count();
	}

	public List<BoardVo> getfindAll(Integer page) {
		return boardRepository.findAll(page);
	}

	public BoardVo getfindById(Long no) {
		return boardRepository.findById(no);
	}

	public void getupdateHit(BoardVo boardVo) {
		boardRepository.updateHit(boardVo);
	}

	public void getinsert(BoardVo boardVo) {
		boardRepository.insert(boardVo);
	}

	public void getdelete(Long boardNo, Long no) {
		boardRepository.delete(boardNo, no);
	}

	public void getinsertComment(BoardVo boardVo) {
		boardRepository.insertComment(boardVo);
	}

	public BoardVo getContents(Long no) {
		BoardVo boardVo = boardRepository.findById(no);

		if (boardVo != null) {
			boardRepository.updateHit(boardVo);
		}

		return boardVo;
	}

}
