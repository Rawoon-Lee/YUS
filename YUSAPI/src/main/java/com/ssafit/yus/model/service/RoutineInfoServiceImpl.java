package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.RoutineInfoDao;
import com.ssafit.yus.model.dto.RoutineInfo;

@Service
public class RoutineInfoServiceImpl implements RoutineInfoService{
	@Autowired
	RoutineInfoDao routineInfoDao;
	@Override
	public List<RoutineInfo> getAll() {
		List<RoutineInfo> list = routineInfoDao.selectAll();
		return list;
	}

}
