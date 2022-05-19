package com.ssafit.yus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ssafit.yus.model.dto.MealBoard;
import com.ssafit.yus.model.dto.MealComm;
import com.ssafit.yus.model.dto.MealLiked;
import com.ssafit.yus.model.service.MealBoardService;
import com.ssafit.yus.model.service.MealCommService;
import com.ssafit.yus.model.service.MealLikedService;

@RestController
@RequestMapping("/meal")
public class MealRestController {
	@Autowired
	private MealBoardService mealBoardService;
	@Autowired
	private MealCommService mealCommService;
	@Autowired
	private MealLikedService mealLikedService;
	
	@GetMapping("/info")
	public ResponseEntity<List<MealBoard>> infolist() {
		return new ResponseEntity<List<MealBoard>>(mealBoardService.getAll(), HttpStatus.OK);
	}
	@GetMapping("/comm")
	public ResponseEntity<List<MealComm>> commlist() {
		return new ResponseEntity<List<MealComm>>(mealCommService.getAll(), HttpStatus.OK);
	}
	@GetMapping("/liked")
	public ResponseEntity<List<MealLiked>> likedlist() {
		return new ResponseEntity<List<MealLiked>>(mealLikedService.getAll(), HttpStatus.OK);
	}
}
