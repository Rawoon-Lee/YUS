package com.ssafit.yus.model.service;

import java.security.NoSuchAlgorithmException;

import com.ssafit.yus.model.dto.UserInfo;
import com.ssafit.yus.model.dto.UserInfoWithFile;

public interface UserInfoService {
	UserInfo selectUserInfo(String id);
	void insertUserInfo(UserInfo userInfo) throws NoSuchAlgorithmException;
	void deleteUserInfo(String id);
	void updateUserInfo(int mode, UserInfo userInfo);
}
