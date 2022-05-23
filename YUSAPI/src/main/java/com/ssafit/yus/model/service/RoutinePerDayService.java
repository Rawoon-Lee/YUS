package com.ssafit.yus.model.service;

import java.util.List;

import com.ssafit.yus.model.dto.RoutinePerDay;

public interface RoutinePerDayService {
	List<RoutinePerDay> selectByGroupNo(int groupNo);
	void insertRoutinePerDay(RoutinePerDay routinePerDay);
}
