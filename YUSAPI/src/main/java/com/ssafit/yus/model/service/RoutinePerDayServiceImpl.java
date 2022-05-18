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
	public List<RoutinePerDay> getAll() {
		List<RoutinePerDay> list = routinePerDayDao.selectAll();
		return list;
	}

}
