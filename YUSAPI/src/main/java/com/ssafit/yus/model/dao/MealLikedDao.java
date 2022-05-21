package com.ssafit.yus.model.dao;

import java.util.List;

import com.ssafit.yus.model.dto.MealLiked;
import com.ssafit.yus.model.dto.RoutineLiked;

public interface MealLikedDao {
	List<MealLiked> selectByPostNo(int postNo);
	MealLiked selectByIds(MealLiked mealLiked);
	void insertMealLiked(MealLiked mealLiked);
	void deleteByIds(MealLiked mealLiked);
}
