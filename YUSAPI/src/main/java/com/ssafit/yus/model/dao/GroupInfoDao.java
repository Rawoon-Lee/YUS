package com.ssafit.yus.model.dao;

import java.util.List;

import com.ssafit.yus.model.dto.GroupInfo;

public interface GroupInfoDao {
	List<GroupInfo> selectAll();
	GroupInfo selectByGroupNo(int groupNo);
	void InsertGroupInfo(GroupInfo groupInfo);
	void updateCurrentPeople(GroupInfo groupInfo);
	void updateGroupPoint(GroupInfo groupInfo);
	void updateGroupInfo(GroupInfo groupInfo);
	void deleteGroup(GroupInfo groupInfo);
	int selectMaxGroupNo();
}
