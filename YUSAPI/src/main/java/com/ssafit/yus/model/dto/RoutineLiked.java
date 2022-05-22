package com.ssafit.yus.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "RoutineLikedDTO", description = "루틴좋아요 정보")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutineLiked {
	private int id;
	private String userId;
	private int routineNo;
}
