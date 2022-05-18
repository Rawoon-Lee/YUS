package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.YoutubeCommDao;
import com.ssafit.yus.model.dto.YoutubeComm;

@Service
public class YoutubeCommServiceImpl implements YoutubeCommService{
	@Autowired
	YoutubeCommDao youtubeCommDao;
	@Override
	public List<YoutubeComm> getAll() {
		List<YoutubeComm> list = youtubeCommDao.selectAll();
		return list;
	}

}
