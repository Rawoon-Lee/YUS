package com.ssafit.yus.tester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssafit.yus.model.dto.ExerciseInfo;
import com.ssafit.yus.model.service.ExerciseInfoService;
import com.ssafit.yus.model.service.ExercisePerRoutineService;
import com.ssafit.yus.model.service.GroupInfoService;
import com.ssafit.yus.model.service.MealBoardService;
import com.ssafit.yus.model.service.MealCommService;
import com.ssafit.yus.model.service.MealLikedService;
import com.ssafit.yus.model.service.RoutineCommService;
import com.ssafit.yus.model.service.RoutineInfoService;
import com.ssafit.yus.model.service.RoutineLikedService;
import com.ssafit.yus.model.service.RoutinePerDayService;
import com.ssafit.yus.model.service.SuccessService;
import com.ssafit.yus.model.service.UserInfoService;
import com.ssafit.yus.model.service.YoutubeCommService;
import com.ssafit.yus.model.service.YoutubeInfoService;
import com.ssafit.yus.model.service.YoutubeLikedService;

public class MapperTester {
	@Autowired
	private ExerciseInfoService exerciseInfoService;
	@Autowired
	private ExercisePerRoutineService exercisePerRoutineService;
	@Autowired
	private GroupInfoService groupInfoService;
	@Autowired
	private MealBoardService mealBoardService;
	@Autowired
	private MealCommService mealCommService;
	@Autowired
	private MealLikedService mealLikedService;
	@Autowired
	private RoutineCommService routineCommService;
	@Autowired
	private RoutineInfoService routineInfoService;
	@Autowired
	private RoutineLikedService routineLikedService;
	@Autowired
	private RoutinePerDayService routinePerDayService;
	@Autowired
	private SuccessService successService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private YoutubeCommService youtubeCommService;
	@Autowired
	private YoutubeInfoService youtubeInfoervice;
	@Autowired
	private YoutubeLikedService youtubeLikedervice;
	
}
