package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.RoutineLikedDao;
import com.ssafit.yus.model.dto.RoutineLiked;

@Service
public class RoutineLikedServiceImpl implements RoutineLikedService{
	@Autowired
	RoutineLikedDao routineLikedDao;
	@Override
	public List<RoutineLiked> getAll() {
		List<RoutineLiked> list = routineLikedDao.selectByRoutineNo(1);
		return list;
	}

}
