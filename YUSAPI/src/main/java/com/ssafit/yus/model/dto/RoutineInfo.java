package com.ssafit.yus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutineInfo {
	private int routineNo;
	private int exerciseType;
	private int userNo;
	private int viewCnt;
}
