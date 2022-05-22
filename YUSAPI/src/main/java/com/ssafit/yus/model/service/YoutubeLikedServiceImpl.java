package com.ssafit.yus.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.YoutubeLikedDao;
import com.ssafit.yus.model.dto.YoutubeLiked;

@Service
public class YoutubeLikedServiceImpl implements YoutubeLikedService{
	@Autowired
	YoutubeLikedDao youtubeLikedDao;

	@Override
	public void insertYoutubeLiked(YoutubeLiked youtubeLiked) {
		youtubeLikedDao.insertYoutubeLiked(youtubeLiked);
	}

	@Override
	public void deleteByIds(YoutubeLiked youtubeLiked) {
		youtubeLikedDao.deleteByIds(youtubeLiked);
	}

	@Override
	public int countLikedByVideoId(String videoId) {
		return youtubeLikedDao.countLikedByVideoId(videoId);
	}

	@Override
	public String checkStatus(YoutubeLiked youtubeLiked) {
		if (youtubeLikedDao.selectByIds(youtubeLiked) != null)
			return "true";
		return "false";
	}
}
