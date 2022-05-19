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
	private String userId;
	private int viewCnt;
	private String regDate;
	private String title;
}
