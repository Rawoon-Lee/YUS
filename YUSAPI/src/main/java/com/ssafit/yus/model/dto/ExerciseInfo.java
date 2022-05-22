package com.ssafit.yus.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "ExerciseInfoDTO", description = "운동 정보")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseInfo {
	private String exerciseName;
	private String exercisePart;
}
