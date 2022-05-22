package com.ssafit.yus.model.service;
import com.ssafit.yus.model.dto.MealLiked;

public interface MealLikedService {
	void insertMealLiked(MealLiked mealLiked);
	void deleteByIds(MealLiked mealLiked);
	int countLikedByPostNo(int postNo);
	String checkStatus(MealLiked mealLiked);
}
