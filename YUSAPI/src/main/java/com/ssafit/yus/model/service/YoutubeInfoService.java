package com.ssafit.yus.model.service;

import java.util.List;

import com.ssafit.yus.model.dto.YoutubeInfo;

public interface YoutubeInfoService {
	List<YoutubeInfo> getAll();
	YoutubeInfo selectById(String videoId);
}
