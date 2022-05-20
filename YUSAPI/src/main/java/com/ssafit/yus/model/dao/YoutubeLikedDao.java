package com.ssafit.yus.model.dao;

import java.util.List;

import com.ssafit.yus.model.dto.YoutubeInfo;
import com.ssafit.yus.model.dto.YoutubeLiked;

public interface YoutubeLikedDao {
	List<YoutubeLiked> selectVideoById(String videoId);
	YoutubeInfo selectByIds(YoutubeLiked youtubeLiked);
	void insertYoutubeLiked(YoutubeLiked youtubeLiked);
	void deleteByIds(YoutubeLiked youtubeLiked);
}
