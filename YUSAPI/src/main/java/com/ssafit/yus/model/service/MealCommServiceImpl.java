package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.MealCommDao;
import com.ssafit.yus.model.dto.MealComm;
import com.ssafit.yus.model.dto.YoutubeComm;

@Service
public class MealCommServiceImpl implements MealCommService{
	@Autowired
	MealCommDao mealCommDao;
	@Override
	public List<MealComm> selectByPostNo(int postNo) {
		return mealCommDao.selectByPostNo(postNo);
	}

	@Override
	public MealComm selectByCommindex(int commIndex) {
		return mealCommDao.selectByCommIndex(commIndex);
	}

	@Override
	public void InsertMealComm(MealComm mealComm) {
		if (mealComm.getClassNo() == 0) {
			int maxCommIndex = mealCommDao.selectMaxCommIndex();
			mealComm.setCommGroup(maxCommIndex + 1);
		}
		mealCommDao.InsertMealComm(mealComm);
	}

	@Override
	public void deleteByCommIndex(int commIndex) {
		mealCommDao.deleteByCommIndex(commIndex);
	}

	@Override
	public void updateForDelete(MealComm mealComm) {
		mealComm.setUserId("deletedcomm");
		mealComm.setComm("작성자에 의해 삭제된 댓글입니다.");
		mealCommDao.updateForDelete(mealComm);
	}

	@Override
	public void updateByCommIndex(MealComm mealComm) {
		mealCommDao.updateByCommIndex(mealComm);
	}
}
