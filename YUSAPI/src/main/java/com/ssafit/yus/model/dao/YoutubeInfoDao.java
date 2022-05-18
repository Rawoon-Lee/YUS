package com.ssafit.yus.model.dao;

import java.util.List;

import com.ssafit.yus.model.dto.YoutubeInfo;

public interface YoutubeInfoDao {
	List<YoutubeInfo> selectAll();
}
