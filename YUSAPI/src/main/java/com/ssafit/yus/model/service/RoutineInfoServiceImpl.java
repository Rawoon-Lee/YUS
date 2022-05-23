package com.ssafit.yus.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.ExercisePerRoutineDao;
import com.ssafit.yus.model.dao.RoutineInfoDao;
import com.ssafit.yus.model.dao.RoutineLikedDao;
import com.ssafit.yus.model.dto.ExercisePerRoutine;
import com.ssafit.yus.model.dto.ListForRoutine;
import com.ssafit.yus.model.dto.RoutineInfo;
import com.ssafit.yus.model.dto.RoutineWithLiked;
import com.ssafit.yus.model.dto.YoutubeInfo;

@Service
public class RoutineInfoServiceImpl implements RoutineInfoService{
	@Autowired
	RoutineInfoDao routineInfoDao;
	@Autowired
	ExercisePerRoutineDao eprDao;
	@Autowired
	RoutineLikedDao routineLikedDao;

	@Override
	public List<Map<String, String>> selectAll() {
		List<Map<String, String>> ret = new ArrayList<>();
		List<RoutineInfo> list = routineInfoDao.selectAll();
		for (RoutineInfo item : list) {
			Map<String, String> temp = new HashMap<>();
			temp.put("routineNo", Integer.toString(item.getRoutineNo()));
			temp.put("userId", item.getUserId());
			temp.put("title", item.getTitle());
			temp.put("regDate", item.getRegDate());
			temp.put("content", item.getContent());
			temp.put("viewCnt", Integer.toString(item.getViewCnt()));
			temp.put("LikedCnt", Integer.toString(routineLikedDao.countLikedByRoutineNo(item.getRoutineNo())));
			ret.add(temp);
		}
		return ret;
	}

	@Override
	public List<Object> selectByRoutineNo(int routineNo){
		List<Object> ret = new ArrayList<>();
		List<ExercisePerRoutine> list = eprDao.selectByRoutineNo(routineNo);
		RoutineInfo temp = routineInfoDao.selectByRoutineNo(routineNo);
		RoutineWithLiked src = new RoutineWithLiked(temp.getRoutineNo(), temp.getUserId(), 
				temp.getViewCnt(), temp.getRegDate(), temp.getTitle(), temp.getContent(), 
				routineLikedDao.countLikedByRoutineNo(routineNo));
		ret.add(src);
		for (ExercisePerRoutine item : list) ret.add(item);
		return ret;
	}

	@Override
	public void insertRoutineInfo(ListForRoutine lfr) {
		routineInfoDao.insertRoutineInfo(lfr.getRoutineInfo());
		int routineNo = routineInfoDao.selectMaxRoutineNo();
		List<ExercisePerRoutine> list = lfr.getEprlist();
		for (ExercisePerRoutine item : list) {
			item.setRoutineNo(routineNo);
			eprDao.InsertEPR(item);
		}
	}

	@Override
	public void updateViewCnt(int routineNo) {
		routineInfoDao.updateViewCnt(routineNo);	
	}
}
