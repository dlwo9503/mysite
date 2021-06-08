package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping("")
	public String board(@RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
			@RequestParam(value = "kwd", required = false) String keyword, Model model) {
		int count = 0;
		List<BoardVo> list = null;

		if (keyword != null) {
			count = boardService.getfindcount(keyword);
			list = boardService.getfindAll2(page, keyword);
		} else {
			count = boardService.getcount();
			list = boardService.getfindAll(page);
		}

		int size = list.size();
		int firstpage = 0;
		int lastpage = (int) Math.ceil(count / 5);

		model.addAttribute("firstPage", firstpage);
		model.addAttribute("lastPage", lastpage);
		model.addAttribute("size", size);
		model.addAttribute("list", list);
		return "board/list";
	}

	@RequestMapping(value = "/view/{no}/{hit}", method = RequestMethod.GET)
	public String view(@PathVariable("no") Long no, @PathVariable("hit") int hit, Model model) {
		BoardVo boardVo = boardService.getfindById(no);
		boardVo.setHit(hit + 1);
		boardService.getupdateHit(boardVo);
		model.addAttribute("vo", boardVo);
		return "board/view";
	}

	@RequestMapping( value="/write", method=RequestMethod.GET )	
	public String write( 
			@RequestParam(value = "page", required = true, defaultValue = "0") Long no,
			String no2, Model model) {
		if(no2 == null) {
			model.addAttribute("no2", "write");
		} else if (no2.equals("comment")) {
			model.addAttribute("no", no);
			model.addAttribute("no2", no2);
		}
		return "board/write";
	}
	
	@Auth
	@RequestMapping( value="/write", method=RequestMethod.POST )	
	public String write(
		@AuthUser UserVo authUser,
		@ModelAttribute BoardVo boardVo,
		String title, 
		String content ) {
		
		if(authUser == null) {
			return	"redirect:/board/";
		}
		
		boardVo.setTitle(title);
		boardVo.setContents(content);
		boardVo.setUserNo( authUser.getNo() );
		
		boardService.getinsert( boardVo );
		
		return	"redirect:/board/";
	}
	
	@Auth
	@RequestMapping( "/delete/{no}" )
	public String delete(
		@AuthUser UserVo authUser,	
		@PathVariable( "no" ) Long boardNo) {
		boardService.getdelete( boardNo, authUser.getNo() );
		return "redirect:/board/";
	}
	
	@Auth
	@RequestMapping( value="/comment/{no2}/{no}" )	
	public String reply(@PathVariable( "no" ) String no2, @PathVariable( "no" ) Long no, Model model) {
		BoardVo boardVo = boardService.getContents( no );
		boardVo.setOrder_no( boardVo.getOrder_no() + 1 );
		boardVo.setDepth( boardVo.getDepth() + 1 );
		
		model.addAttribute( "boardVo", boardVo );
		model.addAttribute( "comment", no2 );
		
		return "board/write";
	}
	
	@Auth
	@RequestMapping( value="/comment/{no}", method=RequestMethod.POST )	
	public String comment(
		@AuthUser UserVo authUser,
		@ModelAttribute BoardVo boardVo,
		String title, 
		String content ) {
		
		if(authUser == null) {
			return	"redirect:/board/";
		}
		
		boardVo.setTitle(title);
		boardVo.setContents(content);
		boardVo.setUserNo( authUser.getNo() );
		
		boardService.getinsertComment( boardVo );
		
		return	"redirect:/board/";
	}
}
