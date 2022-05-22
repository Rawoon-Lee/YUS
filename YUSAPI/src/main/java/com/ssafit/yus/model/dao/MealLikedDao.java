package com.ssafit.yus.model.dao;

import com.ssafit.yus.model.dto.MealLiked;

public interface MealLikedDao {
	int countLikedByPostNo(int postNo);
	MealLiked selectByIds(MealLiked mealLiked);
	void insertMealLiked(MealLiked mealLiked);
	void deleteByIds(MealLiked mealLiked);
}
