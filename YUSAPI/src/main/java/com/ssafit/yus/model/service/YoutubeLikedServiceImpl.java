package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.YoutubeLikedDao;
import com.ssafit.yus.model.dto.YoutubeLiked;

@Service
public class YoutubeLikedServiceImpl implements YoutubeLikedService{
	@Autowired
	YoutubeLikedDao youtubeLikedDao;
	
	@Override
	public List<YoutubeLiked> getAll() {
		List<YoutubeLiked> list = youtubeLikedDao.selectAll();
		return list;
	}

}
