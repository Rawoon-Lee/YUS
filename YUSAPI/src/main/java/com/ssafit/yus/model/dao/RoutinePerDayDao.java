package com.ssafit.yus.model.dao;

import java.util.List;

import com.ssafit.yus.model.dto.RoutinePerDay;

public interface RoutinePerDayDao {
	List<RoutinePerDay> selectAll();
}
