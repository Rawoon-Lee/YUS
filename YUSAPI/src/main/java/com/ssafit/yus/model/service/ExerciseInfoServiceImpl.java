package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.ExerciseInfoDao;
import com.ssafit.yus.model.dto.ExerciseInfo;

@Service
public class ExerciseInfoServiceImpl implements ExerciseInfoService{
	@Autowired
	ExerciseInfoDao exerciseInfoDao;
	
	@Override
	public List<ExerciseInfo> getAll(){
		List<ExerciseInfo>list = exerciseInfoDao.selectAll();
		return list;
	}
}
