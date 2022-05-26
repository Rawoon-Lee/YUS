package com.ssafit.yus.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.MealBoardDao;
import com.ssafit.yus.model.dao.MealLikedDao;
import com.ssafit.yus.model.dto.MealBoard;

@Service
public class MealBoardServiceImpl implements MealBoardService{
	@Autowired
	MealBoardDao mealBoardDao;
	@Autowired
	MealLikedDao mealLikedDao;
	
	@Override
	public List<Map<String, String>> selectAll() {
		List<Map<String, String>> ret = new ArrayList<>();
		List<MealBoard> list = mealBoardDao.selectAll();
		for (MealBoard item : list) {
			Map<String, String> temp = new HashMap<>();
			temp.put("postNo", Integer.toString(item.getPostNo()));
			temp.put("userId", item.getUserId());
			temp.put("title", item.getTitle());
			temp.put("regDate", item.getRegDate());
			temp.put("content", item.getContent());
			temp.put("viewCnt", Integer.toString(item.getViewCnt()));
			temp.put("LikedCnt", Integer.toString(mealLikedDao.countLikedByPostNo(item.getPostNo())));
			temp.put("carb", Integer.toString(item.getCarb()));
			temp.put("protein", Integer.toString(item.getProtein()));
			temp.put("filepath", item.getFilepath());
			temp.put("fat", Integer.toString(item.getFat()));
			ret.add(temp);
		}
		return ret;
	}

	@Override
	public Map<String, String> selectByPostNo(int postNo) {
		Map<String, String> ret = new HashMap<>();
		MealBoard item = mealBoardDao.selectByPostNo(postNo);
		ret.put("postNo", Integer.toString(item.getPostNo()));
		ret.put("userId", item.getUserId());
		ret.put("title", item.getTitle());
		ret.put("regDate", item.getRegDate());
		ret.put("content", item.getContent());
		ret.put("viewCnt", Integer.toString(item.getViewCnt()));
		ret.put("LikedCnt", Integer.toString(mealLikedDao.countLikedByPostNo(item.getPostNo())));
		ret.put("carb", Integer.toString(item.getCarb()));
		ret.put("protein", Integer.toString(item.getProtein()));
		ret.put("filepath", item.getFilepath());
		ret.put("fat", Integer.toString(item.getFat()));
		return ret;
	}

	@Override
	public void insertMealBoard(MealBoard mealBoard) {
		mealBoardDao.insertMealBoard(mealBoard);
	}

	@Override
	public void updateMealBoard(int mode, MealBoard mealBoard) {
		if (mode == 1)
			mealBoardDao.updateMealBoard(mealBoard);
		else
			mealBoardDao.updateFile(mealBoard);
	}

	@Override
	public void updateViewCnt(int postNo) {
		mealBoardDao.updateViewCnt(postNo);
	}

	@Override
	public void deleteMealBoard(int postNo) {
		mealBoardDao.deleteMealBoard(postNo);
	}

}
