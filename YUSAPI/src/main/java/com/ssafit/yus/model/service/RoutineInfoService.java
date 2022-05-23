package com.ssafit.yus.model.service;

import java.util.List;
import java.util.Map;

import com.ssafit.yus.model.dto.ListForRoutine;
import com.ssafit.yus.model.dto.RoutineInfo;

public interface RoutineInfoService {
	List<Map<String, String>> selectAll();
	List<Object> selectByRoutineNo(int routineNo);
	void insertRoutineInfo(ListForRoutine lfr);
	void updateViewCnt(int routineNo);
}
