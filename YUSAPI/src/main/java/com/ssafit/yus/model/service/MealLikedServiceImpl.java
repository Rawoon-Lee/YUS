package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.MealLikedDao;
import com.ssafit.yus.model.dto.MealLiked;

@Service
public class MealLikedServiceImpl implements MealLikedService{
	
	@Autowired
	MealLikedDao mealLikedDao;
	
	@Override
	public List<MealLiked> getAll() {
		List<MealLiked> list = mealLikedDao.selectByPostNo(1);
		return list;
	}

}
