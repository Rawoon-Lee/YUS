package com.ssafit.yus.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.GroupInfoDao;
import com.ssafit.yus.model.dao.RoutinePerDayDao;
import com.ssafit.yus.model.dao.SuccessDao;
import com.ssafit.yus.model.dao.UserInfoDao;
import com.ssafit.yus.model.dto.GroupInfo;
import com.ssafit.yus.model.dto.ListForGroup;
import com.ssafit.yus.model.dto.RoutinePerDay;
import com.ssafit.yus.model.dto.UserInfo;

@Service
public class GroupInfoServiceImpl implements GroupInfoService{
	@Autowired
	GroupInfoDao groupInfoDao;
	@Autowired
	RoutinePerDayDao routinePerDayDao;
	@Autowired
	UserInfoDao userInfoDao;
	@Autowired
	SuccessDao successDao;
	@Autowired
	RoutineInfoService routineInfoService;

	@Override
	public List<GroupInfo> selectAll() {
		return groupInfoDao.selectAll();
	}

	@Override
	public Map<String, Object> selectByGroupNo(int groupNo) {
		Map<String, Object> ret = new HashMap<>();
		List<UserInfo> names = userInfoDao.selectByGroupNo(groupNo);
		List<RoutinePerDay> rpdlist = routinePerDayDao.selectByGroupNo(groupNo);
		ret.put("groupInfo", groupInfoDao.selectByGroupNo(groupNo));
		for (UserInfo name : names)
			ret.put(name.getUserId(), successDao.selectByUserId(name.getUserId()));
		for (RoutinePerDay item : rpdlist)
			ret.put("day" + item.getDays(), routineInfoService.selectByRoutineNo(item.getRoutineNo()));
		return ret;
	}

	@Override
	public void InsertGroupInfo(ListForGroup lfg) {
		groupInfoDao.InsertGroupInfo(lfg.getGroupInfo());
		int groupNo = groupInfoDao.selectMaxGroupNo();
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(lfg.getGroupInfo().getUserId());
		userInfo.setGroupNo(groupNo);
		userInfoDao.updateUserGroup(userInfo);
		List<RoutinePerDay> list = lfg.getRpdlist();
		for (RoutinePerDay item : list) {
			item.setGroupNo(groupNo);
			routinePerDayDao.insertRoutinePerDay(item);
		}
	}

	@Override
	public void updateGroupInfo(int mode, GroupInfo groupInfo) {
		if (mode == 0)
			groupInfoDao.updateGroupInfo(groupInfo);
		else if (mode == 1)
			groupInfoDao.updateGroupPoint(groupInfo);
		else
			groupInfoDao.updateCurrentPeople(groupInfo);
	}

	@Override
	public void deleteGroup(GroupInfo groupInfo) {
		groupInfoDao.deleteGroup(groupInfo);
		List<UserInfo>list = userInfoDao.selectByGroupNo(groupInfo.getGroupNo());
		for (UserInfo item : list) {
			successDao.deleteByUserId(item.getUserId());
			UserInfo userInfo = new UserInfo();
			userInfo.setGroupNo(0);
			userInfo.setUserId(item.getUserId());
			userInfoDao.updateUserGroup(userInfo);
		}
	}
	

}
