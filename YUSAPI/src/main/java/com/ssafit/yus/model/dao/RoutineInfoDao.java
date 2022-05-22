package com.ssafit.yus.model.dao;

import java.util.List;

import com.ssafit.yus.model.dto.RoutineInfo;

public interface RoutineInfoDao {
	List<RoutineInfo> selectAll();
	RoutineInfo selectByRoutineNo(int routineNo);
	void insertRoutineInfo(RoutineInfo routineInfo);
	void updateViewCnt(int routineNo);
	int selectMaxRoutineNo();
}
