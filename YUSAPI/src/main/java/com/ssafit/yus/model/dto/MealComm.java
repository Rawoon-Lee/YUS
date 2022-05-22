package com.ssafit.yus.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "MealCommDTO", description = "식단댓글 정보")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealComm {
	private int commIndex;
	private int classNo;
	private String comm;
	private int orderNo;
	private int commGroup;
	private int postNo;
	private String userId;
	private String regDate;
}
