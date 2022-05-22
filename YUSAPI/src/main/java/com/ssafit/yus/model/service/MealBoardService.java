package com.ssafit.yus.model.service;

import java.util.List;
import java.util.Map;

import com.ssafit.yus.model.dto.MealBoard;

public interface MealBoardService {
	List<Map<String, String>> selectAll();
	Map<String, String> selectByPostNo(int postNo);
	void insertMealBoard(MealBoard mealBoard);
	void updateMealBoard(MealBoard mealBoard);
	void updateViewCnt(int postNo);
	void deleteMealBoard(int postNo);
}
