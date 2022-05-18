package com.ssafit.yus.model.dao;

import java.util.List;

import com.ssafit.yus.model.dto.GroupInfo;

public interface GroupInfoDao {
	List<GroupInfo> selectAll();
}
