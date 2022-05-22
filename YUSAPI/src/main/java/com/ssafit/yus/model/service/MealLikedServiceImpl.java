package com.ssafit.yus.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafit.yus.model.dao.MealLikedDao;
import com.ssafit.yus.model.dto.MealLiked;

@Service
public class MealLikedServiceImpl implements MealLikedService{
	
	@Autowired
	MealLikedDao mealLikedDao;

	@Override
	public void insertMealLiked(MealLiked mealLiked) {
		mealLikedDao.insertMealLiked(mealLiked);
	}

	@Override
	public void deleteByIds(MealLiked mealLiked) {
		mealLikedDao.deleteByIds(mealLiked);
	}

	@Override
	public int countLikedByPostNo(int postNo) {
		return mealLikedDao.countLikedByPostNo(postNo);
	}

	@Override
	public String checkStatus(MealLiked mealLiked) {
		if (mealLikedDao.selectByIds(mealLiked) != null)
			return "true";
		return "false";
	}
}
