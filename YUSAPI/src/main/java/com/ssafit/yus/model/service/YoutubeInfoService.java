package com.ssafit.yus.model.service;

import java.util.List;
import java.util.Map;

import com.ssafit.yus.model.dto.YoutubeInfo;

public interface YoutubeInfoService {
	List<Map<String, String>> getAll();
	Map<String, String> selectById(String videoId);
}
