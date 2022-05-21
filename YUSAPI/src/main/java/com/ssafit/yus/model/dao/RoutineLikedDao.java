package com.ssafit.yus.model.dao;

import java.util.List;

import com.ssafit.yus.model.dto.RoutineLiked;
import com.ssafit.yus.model.dto.YoutubeInfo;
import com.ssafit.yus.model.dto.YoutubeLiked;

public interface RoutineLikedDao {
	List<RoutineLiked> selectByRoutineNo(int routineNo);
	RoutineLiked selectByIds(RoutineLiked routineLiked);
	void insertRoutineLiked(RoutineLiked routineLiked);
	void deleteByIds(RoutineLiked routineLiked);
}
