package com.ssafit.yus.model.dao;
import com.ssafit.yus.model.dto.YoutubeLiked;

public interface YoutubeLikedDao {
	int countLikedByVideoId(String videoId);
	YoutubeLiked selectByIds(YoutubeLiked youtubeLiked);
	void insertYoutubeLiked(YoutubeLiked youtubeLiked);
	void deleteByIds(YoutubeLiked youtubeLiked);
}
