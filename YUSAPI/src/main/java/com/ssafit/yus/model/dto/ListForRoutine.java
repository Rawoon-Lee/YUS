package com.ssafit.yus.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListForRoutine {
	private RoutineInfo routineInfo;
	private List<ExercisePerRoutine> eprlist;
}
