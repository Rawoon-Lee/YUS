package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.SuccessDao;
import com.ssafit.yus.model.dto.Success;

@Service
public class SuccessServiceImpl implements SuccessService{
	@Autowired
	SuccessDao successDao;

	@Override
	public List<Success> selectByUserId(String userId) {
		return successDao.selectByUserId(userId);
	}

	@Override
	public void InsertSuccess(Success success) {
		successDao.insertSuccess(success);
	}


}
