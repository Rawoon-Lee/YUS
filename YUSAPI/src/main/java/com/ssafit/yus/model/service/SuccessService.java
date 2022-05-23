package com.ssafit.yus.model.service;

import java.util.List;

import com.ssafit.yus.model.dto.Success;

public interface SuccessService {
	List<Success> selectByUserId(String userId);
	void InsertSuccess(Success success);
}
