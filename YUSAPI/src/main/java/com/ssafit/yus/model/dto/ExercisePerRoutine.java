package com.ssafit.yus.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "ExercisePerRoutineDTO", description = "루틴 별 운동 정보")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExercisePerRoutine {
	private int id;
	private int routineNo;
	private String exerciseName;
	private int routineSet;
	private int routineRep;
	private int orderNo;
}
