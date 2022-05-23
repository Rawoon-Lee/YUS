package com.ssafit.yus.model.dao;


import java.util.List;

import com.ssafit.yus.model.dto.UserInfo;

public interface UserInfoDao {
	UserInfo selectUserInfo(String id);
	void insertUserInfo(UserInfo userInfo);
	void updateUserInfo(UserInfo userInfo);
	void updateUserGroup(UserInfo userInfo);
	void updateUserPoint(UserInfo userInfo);
	void deleteUserInfo(String id);
	List<String> selectByGroupNo(int groupNo);
}
