package com.ssafit.yus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
