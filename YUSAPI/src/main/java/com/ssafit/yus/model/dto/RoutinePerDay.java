package com.ssafit.yus.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "RoutinePerDayDTO", description = "그룹 별 루틴 정보")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutinePerDay {
	private int id;
	private int days;
	private int groupNo;
	private int routineNo;
}
