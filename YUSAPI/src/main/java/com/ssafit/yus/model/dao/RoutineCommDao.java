package com.ssafit.yus.model.dao;

import java.util.List;

import com.ssafit.yus.model.dto.RoutineComm;
import com.ssafit.yus.model.dto.YoutubeComm;

public interface RoutineCommDao {
	List<RoutineComm> selectByRoutineNo(int routineNo);
	RoutineComm selectByCommIndex(int commIndex);
	void InsertRoutineComm(RoutineComm routineComm);
	void deleteByCommIndex(int commIndex);
	void updateForDelete(RoutineComm routineComm);
	void updateByCommIndex(RoutineComm routineComm);
	int selectMaxCommIndex();
	int selectMaxOrderNo(RoutineComm routineComm);
}
