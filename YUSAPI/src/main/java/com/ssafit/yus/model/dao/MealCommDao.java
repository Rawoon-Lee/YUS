package com.ssafit.yus.model.dao;

import java.util.List;

import com.ssafit.yus.model.dto.MealComm;
import com.ssafit.yus.model.dto.RoutineComm;

public interface MealCommDao {
	List<MealComm> selectByPostNo(int postNo);
	MealComm selectByCommIndex(int commIndex);
	void InsertMealComm(MealComm mealComm);
	void deleteByCommIndex(int commIndex);
	void updateForDelete(MealComm mealComm);
	void updateByCommIndex(MealComm mealComm);
	int selectMaxCommIndex();
	int selectMaxOrderNo(MealComm mealComm);
}
