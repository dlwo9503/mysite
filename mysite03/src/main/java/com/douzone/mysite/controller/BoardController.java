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

import com.douzone.mysite.repository.BoardRepository;
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
	@RequestMapping( value="/comment/{no}", method=RequestMethod.GET)	
	public String comment(@PathVariable( "no" ) Long no, 
			@AuthUser UserVo authUser,
			@ModelAttribute BoardVo boardVo,
			String title, 
			String content,
			Model model) {
		BoardVo vo = boardService.getfindById(no);
		int groupNo = vo.getGroup_no();
		int depth = vo.getDepth();
		int orderNo = vo.getOrder_no();
		if (vo.getDepth() == 0) {
			vo = new BoardVo();
			boardVo.setTitle(title);
			boardVo.setContents(content);
			boardVo.setUserNo( authUser.getNo() );
			vo.setGroup_no(groupNo);
			vo.setOrder_no(1);
			vo.setDepth(depth + 1);
			new BoardRepository().updatComment(groupNo);
			new BoardRepository().insertComment(vo);
		} else if (vo.getDepth() >= 1) {
			vo = new BoardVo();
			boardVo.setTitle(title);
			boardVo.setContents(content);
			boardVo.setUserNo( authUser.getNo() );
			vo.setGroup_no(groupNo);
			vo.setOrder_no(orderNo + 1);
			vo.setDepth(depth + 1);
			new BoardRepository().updatComment2(groupNo, orderNo);
			new BoardRepository().insertComment(vo);
		}
		
		model.addAttribute( "vo", vo );
		
		return "/board/write";
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
