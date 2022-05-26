package com.ssafit.yus.model.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.GroupInfoDao;
import com.ssafit.yus.model.dao.UserInfoDao;
import com.ssafit.yus.model.dto.GroupInfo;
import com.ssafit.yus.model.dto.UserInfo;
import com.ssafit.yus.model.dto.UserInfoWithFile;
import com.ssafit.yus.util.SHA256;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	@Autowired
	UserInfoDao userInfoDao;
	@Autowired
	GroupInfoDao groupInfoDao;

	@Override
	public UserInfo selectUserInfo(String id) {
		return userInfoDao.selectUserInfo(id);
	}

	@Override
	public void insertUserInfo(UserInfo userInfo) throws NoSuchAlgorithmException{
		String pw = new SHA256().getHash(userInfo.getUserPassword());
		userInfo.setUserPassword(pw);
		userInfoDao.insertUserInfo(userInfo);
	}
	
	@Override
	public void deleteUserInfo(String id) {
		userInfoDao.deleteUserInfo(id);
	}

	@Override
	public void updateUserInfo(int mode, UserInfo userInfo){
		if (mode == 0)
			userInfoDao.updateUserInfo(userInfo);
		else if (mode == 1)
			userInfoDao.updateUserPoint(userInfo);
		else if (mode == 2){
			if (userInfo.getGroupNo() != 0) {
				GroupInfo groupInfo = new GroupInfo();
				groupInfo.setGroupNo(userInfo.getGroupNo());
				groupInfoDao.updateCurrentPeople(groupInfo);
			}
			userInfoDao.updateUserGroup(userInfo);
		}
		else
			userInfoDao.updateUserFile(userInfo);
	}
}
