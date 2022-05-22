package com.ssafit.yus.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.RoutineLikedDao;
import com.ssafit.yus.model.dto.RoutineLiked;

@Service
public class RoutineLikedServiceImpl implements RoutineLikedService{
	@Autowired
	RoutineLikedDao routineLikedDao;
	
	@Override
	public void insertRoutineLiked(RoutineLiked routineLiked) {
		routineLikedDao.insertRoutineLiked(routineLiked);
	}

	@Override
	public void deleteByIds(RoutineLiked routineLiked) {
		routineLikedDao.deleteByIds(routineLiked);
	}

	@Override
	public int countLikedByRoutineNo(int routineNo) {
		return routineLikedDao.countLikedByRoutineNo(routineNo);
	}

	@Override
	public String checkStatus(RoutineLiked routineLiked) {
		if (routineLikedDao.selectByIds(routineLiked) != null)
			return "true";
		return "false";
	}
}
