package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.RoutinePerDayDao;
import com.ssafit.yus.model.dto.RoutinePerDay;

@Service
public class RoutinePerDayServiceImpl implements RoutinePerDayService{
	@Autowired
	RoutinePerDayDao routinePerDayDao;
	
	@Override
	public List<RoutinePerDay> selectByGroupNo(int groupNo) {
		return routinePerDayDao.selectByGroupNo(groupNo);
	}
	@Override
	public void insertRoutinePerDay(RoutinePerDay routinePerDay) {
		routinePerDayDao.insertRoutinePerDay(routinePerDay);
	}
}
