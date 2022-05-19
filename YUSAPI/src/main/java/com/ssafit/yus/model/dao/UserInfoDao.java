package com.ssafit.yus.model.dao;


import com.ssafit.yus.model.dto.UserInfo;

public interface UserInfoDao {
	UserInfo selectUserInfo(String id);
	void insertUserInfo(UserInfo userInfo);
	void updateUserInfo(UserInfo userInfo);
	void deleteUserInfo(String id);
}
