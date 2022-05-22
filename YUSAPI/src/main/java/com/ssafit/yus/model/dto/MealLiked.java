package com.ssafit.yus.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "MealLikedDTO", description = "식단좋아요 정보")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealLiked {
	private int id;
	private String userId;
	private int postNo;
}
