package com.ssafit.yus.model.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.UserInfoDao;
import com.ssafit.yus.model.dto.UserInfo;
import com.ssafit.yus.util.SHA256;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	@Autowired
	UserInfoDao userInfoDao;

	@Override
	public UserInfo selectUserInfo(String id) {
		// TODO Auto-generated method stub
		return userInfoDao.selectUserInfo(id);
	}

	@Override
	public void insertUserInfo(UserInfo userInfo) throws NoSuchAlgorithmException{
		String pw = new SHA256().getHash(userInfo.getUserPassword());
		userInfo.setUserPassword(pw);
		userInfoDao.insertUserInfo(userInfo);
	}

	@Override
	public void updateGroupNo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserPoint(UserInfo userInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserInfo(String id) {
		// TODO Auto-generated method stub
		
	}
	
}
