package com.ssafit.yus.model.dao;

import java.util.List;

import com.ssafit.yus.model.dto.MealBoard;

public interface MealBoardDao {
	List<MealBoard> selectAll();
	MealBoard selectByPostNo(int postNo);
	void insertMealBoard(MealBoard mealBoard);
	void updateMealBoard(MealBoard mealBoard);
	void updateFile(MealBoard mealBoard);
	void updateViewCnt(int postNo);
	void deleteMealBoard(int postNo);
}
