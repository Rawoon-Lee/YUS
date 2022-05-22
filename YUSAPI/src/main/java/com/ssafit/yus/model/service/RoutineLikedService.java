package com.ssafit.yus.model.service;

import com.ssafit.yus.model.dto.RoutineLiked;

public interface RoutineLikedService {
	void insertRoutineLiked(RoutineLiked routineLiked);
	void deleteByIds(RoutineLiked routineLiked);
	int countLikedByRoutineNo(int routineNo);
	String checkStatus(RoutineLiked routineLiked);
}
