package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.MealBoardDao;
import com.ssafit.yus.model.dto.MealBoard;

@Service
public class MealBoardServiceImpl implements MealBoardService{
	@Autowired
	MealBoardDao mealBoardDao;
	
	@Override
	public List<MealBoard> getAll() {
		List<MealBoard> list = mealBoardDao.selectAll();
		return list;
	}

}
