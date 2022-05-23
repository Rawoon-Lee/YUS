package com.ssafit.yus.model.dao;

import java.util.List;

import com.ssafit.yus.model.dto.Success;

public interface SuccessDao {
	List<Success> selectByUserId(String userId);
	void insertSuccess(Success success);
	void deleteByUserId(String userId);
}
