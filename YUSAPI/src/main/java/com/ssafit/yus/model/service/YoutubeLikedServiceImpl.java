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
	public void insertYoutubeLiked(YoutubeLiked youtubeLiked) {
		youtubeLikedDao.insertYoutubeLiked(youtubeLiked);
	}

	@Override
	public void deleteByIds(YoutubeLiked youtubeLiked) {
		youtubeLikedDao.deleteByIds(youtubeLiked);
	}

	@Override
	public int countLikedByVideoId(String videoId) {
		int ret = 0;
		List<YoutubeLiked> forCount = youtubeLikedDao.selectByVideoId(videoId);
		if (forCount != null) ret = forCount.size();
		return ret;
	}

	@Override
	public String checkStatus(YoutubeLiked youtubeLiked) {
		if (youtubeLikedDao.selectByIds(youtubeLiked) != null)
			return "true";
		return "false";
	}
	
}
