package com.ssafit.yus.model.service;

import java.util.List;

import com.ssafit.yus.model.dto.YoutubeComm;

public interface YoutubeCommService {
	List<YoutubeComm> selectByVideoId(String videoId);
	YoutubeComm selectByCommindex(int commIndex);
	void InsertYoutubeComm(YoutubeComm youtubeComm);
	void deleteByCommIndex(int commIndex);
	void updateForDelete(YoutubeComm youtubeComm);
	void updateByCommIndex(YoutubeComm youtubeComm);
}
