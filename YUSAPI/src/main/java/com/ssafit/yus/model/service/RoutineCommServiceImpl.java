package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.RoutineCommDao;
import com.ssafit.yus.model.dto.RoutineComm;

@Service
public class RoutineCommServiceImpl implements RoutineCommService{
	@Autowired
	RoutineCommDao routineCommDao;
	@Override
	public List<RoutineComm> getAll() {
		List<RoutineComm> list = routineCommDao.selectAll();
		return list;
	}

}
