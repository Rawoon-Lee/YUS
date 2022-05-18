package com.ssafit.yus.model.dao;

import java.util.List;

import com.ssafit.yus.model.dto.UserInfo;

public interface UserInfoDao {
	List<UserInfo> selectAll();
}
