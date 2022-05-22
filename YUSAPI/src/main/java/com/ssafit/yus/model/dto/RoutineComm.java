package com.ssafit.yus.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "RoutineCommDTO", description = "루틴댓글 정보")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutineComm {
	private int commIndex;
	private int classNo;
	private String comm;
	private int orderNo;
	private int commGroup;
	private int routineNo;
	private String userId;
	private String regDate;
}
