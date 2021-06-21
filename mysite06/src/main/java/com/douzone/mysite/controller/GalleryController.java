package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GalleryVo> list = galleryService.findAll();
		model.addAttribute("list", list);
		return "gallery/index";
	}

	@Auth(role="ADMIN") // admin으로 로그인 했을 때만
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(GalleryVo vo,
			@RequestParam("file") MultipartFile file) {
		
			String url = fileUploadService.restore(file);
			vo.setUrl(url);
			galleryService.upload(vo);
			return "redirect:/gallery";
	}
	
	@Auth(role="ADMIN") // admin으로 로그인 했을 때만
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable("no") int no) {
		galleryService.delete(no);
		return "redirect:/gallery/";
	}
}
