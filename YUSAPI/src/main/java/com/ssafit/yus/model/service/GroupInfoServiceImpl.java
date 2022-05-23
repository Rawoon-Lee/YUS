package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.GroupInfoDao;
import com.ssafit.yus.model.dao.RoutinePerDayDao;
import com.ssafit.yus.model.dao.SuccessDao;
import com.ssafit.yus.model.dao.UserInfoDao;
import com.ssafit.yus.model.dto.GroupInfo;
import com.ssafit.yus.model.dto.ListForGroup;
import com.ssafit.yus.model.dto.RoutinePerDay;

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

	@Override
	public List<GroupInfo> selectAll() {
		return groupInfoDao.selectAll();
	}

	@Override
	public GroupInfo selectByGroupNo(int groupNo) {
		return groupInfoDao.selectByGroupNo(groupNo);
	}

	@Override
	public void InsertGroupInfo(ListForGroup lfg) {
		groupInfoDao.InsertGroupInfo(lfg.getGroupInfo());
		int groupNo = groupInfoDao.selectMaxGroupNo();
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
		List<String>list = userInfoDao.selectByGroupNo(groupInfo.getGroupNo());
		for (String item : list) {
			successDao.deleteByUserId(item);
		}
	}
	

}
