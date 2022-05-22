package com.ssafit.yus.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "RoutineInfoDTO", description = "루틴정보")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutineInfo {
	private int routineNo;
	private String userId;
	private int viewCnt;
	private String regDate;
	private String title;
	private String content;
}
