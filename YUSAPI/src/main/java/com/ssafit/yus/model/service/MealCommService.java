package com.ssafit.yus.model.service;

import java.util.List;
import com.ssafit.yus.model.dto.MealComm;
import com.ssafit.yus.model.dto.RoutineComm;

public interface MealCommService {
	List<MealComm> selectByPostNo(int postNo);
	MealComm selectByCommindex(int commIndex);
	void InsertMealComm(MealComm mealComm);
	void deleteByCommIndex(int commIndex);
	void updateForDelete(MealComm mealComm);
	void updateByCommIndex(MealComm mealComm);
}
