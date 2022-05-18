package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.UserInfoDao;
import com.ssafit.yus.model.dto.UserInfo;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	@Autowired
	UserInfoDao userInfoDao;
	
	@Override
	public List<UserInfo> getAll() {
		List<UserInfo> list = userInfoDao.selectAll();
		return list;
	}
	
}
