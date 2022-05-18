package com.ssafit.yus.model.dao;

import java.util.List;

import com.ssafit.yus.model.dto.ExerciseInfo;

public interface ExerciseInfoDao {
	List<ExerciseInfo> selectAll();
}
