package com.ssafit.yus.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.yus.model.dto.ExerciseInfo;
import com.ssafit.yus.model.dto.ExercisePerRoutine;
import com.ssafit.yus.model.dto.UserInfo;
import com.ssafit.yus.model.service.ExerciseInfoService;
import com.ssafit.yus.model.service.ExercisePerRoutineService;
import com.ssafit.yus.model.service.UserInfoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/user")
public class UserRestController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	@Autowired
	private UserInfoService userInfoService;
	
	@PostMapping("/login")
	public ResponseEntity<UserInfo> login(@RequestBody UserInfo userInfo) {
		return new ResponseEntity<UserInfo>(userInfoService.selectUserInfo(userInfo.getUserId()), HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserInfo userInfo) throws NoSuchAlgorithmException {
		try {
			if (userInfoService.selectUserInfo(userInfo.getUserId()) == null) {
				userInfoService.insertUserInfo(userInfo);
				return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
			}
			else
				return new ResponseEntity<String>(FAIL, HttpStatus.CONFLICT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
