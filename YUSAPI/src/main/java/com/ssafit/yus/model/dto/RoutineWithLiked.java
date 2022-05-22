package com.ssafit.yus.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "RoutineWithLikedDTO", description = "루틴정보 + 라이크 카운트")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutineWithLiked {
	private int routineNo;
	private String userId;
	private int viewCnt;
	private String regDate;
	private String title;
	private String content;
	private int LikedCnt;
}
