package com.douzone.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Component
public class initController implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private ServletContext application; // application scope 사용
	
	@Autowired
	private SiteService siteService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		SiteVo vo = siteService.findAll();
		application.setAttribute("title", vo.getTitle());
	}
}