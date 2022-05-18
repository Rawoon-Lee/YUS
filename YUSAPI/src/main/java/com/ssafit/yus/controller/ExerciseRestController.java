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
import com.ssafit.yus.model.service.ExerciseInfoService;
import com.ssafit.yus.model.service.ExercisePerRoutineService;

@RestController
@RequestMapping("/exercise")
public class ExerciseRestController {
	@Autowired
	private ExerciseInfoService exerciseInfoService;
	@Autowired
	private ExercisePerRoutineService exercisePerRoutineService;
	
	@GetMapping("/info")
	public ResponseEntity<List<ExerciseInfo>> infolist() {
		return new ResponseEntity<List<ExerciseInfo>>(exerciseInfoService.getAll(), HttpStatus.OK);
	}
	@GetMapping("/epr")
	public ResponseEntity<List<ExercisePerRoutine>> eprlist() {
		return new ResponseEntity<List<ExercisePerRoutine>>(exercisePerRoutineService.getAll(), HttpStatus.OK);
	}
}
