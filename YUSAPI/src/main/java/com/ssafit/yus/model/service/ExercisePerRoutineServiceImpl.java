package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.ExercisePerRoutineDao;
import com.ssafit.yus.model.dto.ExercisePerRoutine;

@Service
public class ExercisePerRoutineServiceImpl implements ExercisePerRoutineService{
	@Autowired
	ExercisePerRoutineDao exercisePerRoutineDao;
	
	@Override
	public List<ExercisePerRoutine> getAll() {
		List<ExercisePerRoutine> list = exercisePerRoutineDao.selectByRoutineNo(1);
		return list;
	}

}
