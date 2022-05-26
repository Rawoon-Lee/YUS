package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.RoutineCommDao;
import com.ssafit.yus.model.dto.MealComm;
import com.ssafit.yus.model.dto.RoutineComm;

@Service
public class RoutineCommServiceImpl implements RoutineCommService{
	@Autowired
	RoutineCommDao routineCommDao;
	
	@Override
	public List<RoutineComm> selectByRoutineNo(int routineNo) {
		return routineCommDao.selectByRoutineNo(routineNo);
	}

	@Override
	public RoutineComm selectByCommindex(int commIndex) {
		return routineCommDao.selectByCommIndex(commIndex);
	}

	@Override
	public void InsertRoutineComm(RoutineComm routineComm) {
		if (routineComm.getClassNo() == 0) {
			int maxCommIndex = routineCommDao.selectMaxCommIndex();
			routineComm.setCommGroup(maxCommIndex + 1);
		}
		else {
			int max = routineCommDao.selectMaxOrderNo(routineComm) + 1;
			routineComm.setOrderNo(max);
		}
		routineCommDao.InsertRoutineComm(routineComm);
	}

	@Override
	public void deleteByCommIndex(int commIndex) {
		routineCommDao.deleteByCommIndex(commIndex);
	}

	@Override
	public void updateForDelete(RoutineComm routineComm) {
		routineComm.setUserId("deletedcomm");
		routineComm.setComm("작성자에 의해 삭제된 댓글입니다.");
		routineCommDao.updateForDelete(routineComm);
	}

	@Override
	public void updateByCommIndex(RoutineComm routineComm) {
		routineCommDao.updateByCommIndex(routineComm);
	}
}
