package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.GroupInfoDao;
import com.ssafit.yus.model.dto.GroupInfo;

@Service
public class GroupInfoServiceImpl implements GroupInfoService{
	@Autowired
	GroupInfoDao groupInfoDao;
	
	@Override
	public List<GroupInfo> getAll() {
		List<GroupInfo> list = groupInfoDao.selectAll();
		return list;
	}

}
