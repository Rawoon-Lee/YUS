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
import com.ssafit.yus.model.dto.RoutineComm;
import com.ssafit.yus.model.dto.RoutineInfo;
import com.ssafit.yus.model.dto.RoutineLiked;
import com.ssafit.yus.model.dto.RoutinePerDay;
import com.ssafit.yus.model.service.ExerciseInfoService;
import com.ssafit.yus.model.service.ExercisePerRoutineService;
import com.ssafit.yus.model.service.RoutineCommService;
import com.ssafit.yus.model.service.RoutineInfoService;
import com.ssafit.yus.model.service.RoutineLikedService;
import com.ssafit.yus.model.service.RoutinePerDayService;

@RestController
@RequestMapping("/routine")
public class RoutineRestController {
	@Autowired
	private RoutineCommService routineCommService;
	@Autowired
	private RoutineInfoService routineInfoService;
	@Autowired
	private RoutineLikedService routineLikedService;
	@Autowired
	private RoutinePerDayService routinePerDayService;
	
	@GetMapping("/info")
	public ResponseEntity<List<RoutineInfo>> infolist() {
		return new ResponseEntity<List<RoutineInfo>>(routineInfoService.getAll(), HttpStatus.OK);
	}
	@GetMapping("/liked")
	public ResponseEntity<List<RoutineLiked>> likedlist() {
		return new ResponseEntity<List<RoutineLiked>>(routineLikedService.getAll(), HttpStatus.OK);
	}
	@GetMapping("/comm")
	public ResponseEntity<List<RoutineComm>> commlist() {
		return new ResponseEntity<List<RoutineComm>>(routineCommService.getAll(), HttpStatus.OK);
	}
	@GetMapping("/rpd")
	public ResponseEntity<List<RoutinePerDay>> rpdlist() {
		return new ResponseEntity<List<RoutinePerDay>>(routinePerDayService.getAll(), HttpStatus.OK);
	}
}
