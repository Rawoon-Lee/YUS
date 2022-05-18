package com.ssafit.yus.model.dao;

import java.util.List;

import com.ssafit.yus.model.dto.MealBoard;

public interface MealBoardDao {
	List<MealBoard> selectAll();
}
