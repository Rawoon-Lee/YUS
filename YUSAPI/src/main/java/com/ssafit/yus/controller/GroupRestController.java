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
import com.ssafit.yus.model.dto.GroupInfo;
import com.ssafit.yus.model.dto.Success;
import com.ssafit.yus.model.service.ExerciseInfoService;
import com.ssafit.yus.model.service.ExercisePerRoutineService;
import com.ssafit.yus.model.service.GroupInfoService;
import com.ssafit.yus.model.service.SuccessService;

@RestController
@RequestMapping("/group")
public class GroupRestController {
	@Autowired
	private GroupInfoService groupInfoService;
	@Autowired
	private SuccessService successService;
	
	@GetMapping("/info")
	public ResponseEntity<List<GroupInfo>> infolist() {
		return new ResponseEntity<List<GroupInfo>>(groupInfoService.getAll(), HttpStatus.OK);
	}
	@GetMapping("/success")
	public ResponseEntity<List<Success>> successlist() {
		return new ResponseEntity<List<Success>>(successService.getAll(), HttpStatus.OK);
	}
}
