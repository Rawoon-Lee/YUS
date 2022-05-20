package com.ssafit.yus.model.dao;

import java.util.List;

import com.ssafit.yus.model.dto.YoutubeComm;

public interface YoutubeCommDao {
	List<YoutubeComm> selectByVideoId(String videoId);
	YoutubeComm selectByCommindex(int commIndex);
	void InsertYoutubeComm(YoutubeComm youtubeComm);
	void deleteByCommIndex(int commIndex);
	void updateForDelete(YoutubeComm youtubeComm);
	void updateByCommIndex(YoutubeComm youtubeComm);
	int selectMaxCommIndex();
}
