package com.ssafit.yus.model.service;

import java.security.NoSuchAlgorithmException;

import com.ssafit.yus.model.dto.UserInfo;

public interface UserInfoService {
	UserInfo selectUserInfo(String id);
	void insertUserInfo(UserInfo userInfo) throws NoSuchAlgorithmException;
	void deleteUserInfo(String id);
	void updateUserInfo(UserInfo userInfo);
}
