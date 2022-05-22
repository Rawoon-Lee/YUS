package com.ssafit.yus.model.dao;

import java.util.List;

import com.ssafit.yus.model.dto.ExercisePerRoutine;

public interface ExercisePerRoutineDao {
	List<ExercisePerRoutine> selectByRoutineNo(int routineNo);
	void InsertEPR(ExercisePerRoutine epr);
}
