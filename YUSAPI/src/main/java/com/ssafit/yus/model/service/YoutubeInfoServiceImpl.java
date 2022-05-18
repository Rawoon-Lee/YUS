package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.YoutubeInfoDao;
import com.ssafit.yus.model.dto.YoutubeInfo;

@Service
public class YoutubeInfoServiceImpl implements YoutubeInfoService{
	@Autowired
	YoutubeInfoDao youtubeInfoDao;
	@Override
	public List<YoutubeInfo> getAll() {
		List<YoutubeInfo> list = youtubeInfoDao.selectAll();
		return list;
	}

}
