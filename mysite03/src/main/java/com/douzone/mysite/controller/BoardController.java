package com.douzone.mysite.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
@RequestMapping( "/board" )
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping( "" )
	public String board(
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword,
		Model model ) {
		
		Map<String, Object> map = boardService.getContentsList( page, keyword );
		model.addAttribute("map", map);
		//model.addAllAttributes(map);
		
		return "board/list";
	}
	
	@RequestMapping( "/view/{no}" )
	public String view( @PathVariable( "no" ) Long no, Model model ) {
		BoardVo boardVo = boardService.getContents( no );
		model.addAttribute( "boardVo", boardVo );
		return "board/view";
	}
	
	@Auth
	@RequestMapping( "/delete/{no}" )
	public String delete(
		@AuthUser UserVo authUser,	
		@PathVariable( "no" ) Long boardNo,
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword ) {
		boardService.deleteContents( boardNo, authUser.getNo() );
		return "redirect:/board?p=" + page;
	}
	
	@Auth
	@RequestMapping( value="/modify/{no}" )	
	public String modify(
		@AuthUser UserVo authUser,
		@PathVariable( "no" ) Long no,
		Model model) {
		BoardVo boardVo = boardService.getContents(no, authUser.getNo() );
		model.addAttribute( "boardVo", boardVo );
		return "board/modify";
	}

	@Auth
	@RequestMapping( value="/modify", method=RequestMethod.POST )	
	public String modify(
		@AuthUser UserVo authUser,
		@ModelAttribute BoardVo boardVo,
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword ) {
		boardVo.setUserNo( authUser.getNo() );
		boardService.modifyContents( boardVo );
		return "redirect:/board/view/" + boardVo.getNo() + 
				"?p=" + page;
	}

	@Auth
	@RequestMapping( value="/write", method=RequestMethod.GET )	
	public String write() {
		return "board/write";
	}

	@Auth	
	@RequestMapping( value="/write", method=RequestMethod.POST )	
	public String write(
		@AuthUser UserVo authUser,
		@ModelAttribute @Valid BoardVo boardVo, BindingResult result, Model model,
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword ) {
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel()); // All을 사용하면 map의 키와 값을 모두 넘길 수 있음
			return "board/write";
		}
		boardVo.setUserNo( authUser.getNo() );
		boardService.addContents( boardVo );
		
		return	"redirect:/board?p=" + page;
	}

	@Auth
	@RequestMapping( value="/comment/{no}" )	
	public String reply(@PathVariable( "no" ) Long no, Model model) {
		BoardVo boardVo = boardService.getContents( no );
		boardVo.setOrder_no( boardVo.getOrder_no() + 1 );
		boardVo.setDepth( boardVo.getDepth() + 1 );
		
		model.addAttribute( "boardVo", boardVo );
		
		return "board/comment";
	}	
}