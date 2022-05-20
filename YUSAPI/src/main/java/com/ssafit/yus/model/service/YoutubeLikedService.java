package com.ssafit.yus.model.service;
import com.ssafit.yus.model.dto.YoutubeLiked;

public interface YoutubeLikedService {
	void insertYoutubeLiked(YoutubeLiked youtubeLiked);
	void deleteByIds(YoutubeLiked youtubeLiked);
	int countLikedByVideoId(String videoId);
	String checkStatus(YoutubeLiked youtubeLiked);
}
