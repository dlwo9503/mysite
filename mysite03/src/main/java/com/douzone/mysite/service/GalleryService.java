package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GalleryRepository;
import com.douzone.mysite.vo.GalleryVo;

@Service
public class GalleryService {
	@Autowired
	GalleryRepository galleryRepository;
	
	public List<GalleryVo> findAll() {
		return galleryRepository.findAll();
	}
	
	public void upload(GalleryVo galleryVo) {
		galleryRepository.upload(galleryVo);
	}

	public void delete(int no) {
		galleryRepository.delete(no);
	}
}
