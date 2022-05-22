package com.ssafit.yus.model.dao;
import com.ssafit.yus.model.dto.RoutineLiked;

public interface RoutineLikedDao {
	int countLikedByRoutineNo(int routineNo);
	RoutineLiked selectByIds(RoutineLiked routineLiked);
	void insertRoutineLiked(RoutineLiked routineLiked);
	void deleteByIds(RoutineLiked routineLiked);
}
