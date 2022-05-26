package com.ssafit.yus.model.dao;


import java.util.List;

import com.ssafit.yus.model.dto.UserInfo;
import com.ssafit.yus.model.dto.UserInfoWithFile;

public interface UserInfoDao {
	UserInfo selectUserInfo(String id);
	void insertUserInfo(UserInfo userInfo);
	void updateUserInfo(UserInfo userInfo);
	void updateUserGroup(UserInfo userInfo);
	void updateUserPoint(UserInfo userInfo);
	void updateUserFile(UserInfo userInfo);
	void deleteUserInfo(String id);
	List<UserInfo> selectByGroupNo(int groupNo);
}
