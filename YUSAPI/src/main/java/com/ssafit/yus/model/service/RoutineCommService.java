package com.ssafit.yus.model.service;

import java.util.List;

import com.ssafit.yus.model.dto.RoutineComm;
import com.ssafit.yus.model.dto.YoutubeComm;

public interface RoutineCommService {
	List<RoutineComm> selectByRoutineNo(int routineNo);
	RoutineComm selectByCommindex(int commIndex);
	void InsertRoutineComm(RoutineComm routineComm);
	void deleteByCommIndex(int commIndex);
	void updateForDelete(RoutineComm routineComm);
	void updateByCommIndex(RoutineComm routineComm);
}
