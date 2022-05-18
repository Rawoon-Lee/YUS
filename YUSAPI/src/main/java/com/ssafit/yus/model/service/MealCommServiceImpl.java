package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.MealCommDao;
import com.ssafit.yus.model.dto.MealComm;

@Service
public class MealCommServiceImpl implements MealCommService{
	@Autowired
	MealCommDao mealCommDao;
	@Override
	public List<MealComm> getAll() {
		List<MealComm> list = mealCommDao.selectAll();
		return list;
	}

}
