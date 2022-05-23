package com.ssafit.yus.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.YoutubeInfoDao;
import com.ssafit.yus.model.dao.YoutubeLikedDao;
import com.ssafit.yus.model.dto.YoutubeInfo;

@Service
public class YoutubeInfoServiceImpl implements YoutubeInfoService{
	@Autowired
	YoutubeInfoDao youtubeInfoDao;
	@Autowired
	YoutubeLikedDao youtubeLikedDao;
	
	@Override
	public List<Map<String, String>> getAll() {
		List<Map<String, String>> ret = new ArrayList<>();
		List<YoutubeInfo> list = youtubeInfoDao.selectAll();
		for (YoutubeInfo item : list) {
			Map<String, String> temp = new HashMap<>();
			temp.put("videoId", item.getVideoId());
			temp.put("url", item.getUrl());
			temp.put("title", item.getTitle());
			temp.put("channelName", item.getChannelName());
			temp.put("exerciseName", item.getExerciseName());
			temp.put("viewCnt", Integer.toString(item.getViewCnt()));
			temp.put("LikedCnt", Integer.toString(youtubeLikedDao.countLikedByVideoId(item.getVideoId())));
			ret.add(temp);
		}
		return ret;
	}
	@Override
	public Map<String, String> selectById(String videoId) {
		Map<String, String> ret = new HashMap<>();
		YoutubeInfo item = youtubeInfoDao.selectById(videoId);
		ret.put("videoId", item.getVideoId());
		ret.put("url", item.getUrl());
		ret.put("title", item.getTitle());
		ret.put("channelName", item.getChannelName());
		ret.put("exerciseName", item.getExerciseName());
		ret.put("viewCnt", Integer.toString(item.getViewCnt()));
		ret.put("LikedCnt", Integer.toString(youtubeLikedDao.countLikedByVideoId(item.getVideoId())));
		return ret;
	}
	@Override
	public void updateViewCnt(String videoId) {
		youtubeInfoDao.updateViewCnt(videoId);
	}
}
