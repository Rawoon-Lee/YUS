package com.ssafit.yus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.yus.model.dto.ExerciseInfo;
import com.ssafit.yus.model.dto.ExercisePerRoutine;
import com.ssafit.yus.model.dto.UserInfo;
import com.ssafit.yus.model.service.ExerciseInfoService;
import com.ssafit.yus.model.service.ExercisePerRoutineService;
import com.ssafit.yus.model.service.UserInfoService;

@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	private UserInfoService userInfoService;
	
	@GetMapping("/info")
	public ResponseEntity<List<UserInfo>> infolist() {
		return new ResponseEntity<List<UserInfo>>(userInfoService.getAll(), HttpStatus.OK);
	}
}
