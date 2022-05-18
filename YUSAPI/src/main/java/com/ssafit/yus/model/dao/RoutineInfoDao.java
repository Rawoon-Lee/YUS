package com.ssafit.yus.model.dao;

import java.util.List;

import com.ssafit.yus.model.dto.RoutineInfo;

public interface RoutineInfoDao {
	List<RoutineInfo> selectAll();
}
