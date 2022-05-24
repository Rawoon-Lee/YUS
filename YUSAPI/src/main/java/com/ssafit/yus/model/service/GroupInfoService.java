package com.ssafit.yus.model.service;

import java.util.List;
import java.util.Map;

import com.ssafit.yus.model.dto.GroupInfo;
import com.ssafit.yus.model.dto.ListForGroup;

public interface GroupInfoService {
	List<GroupInfo> selectAll();
	Map<String, Object> selectByGroupNo(int groupNo);
	void InsertGroupInfo(ListForGroup lfg);
	void updateGroupInfo(int mode, GroupInfo groupInfo);
	void deleteGroup(GroupInfo groupInfo);
}
