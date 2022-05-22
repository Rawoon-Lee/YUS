package com.ssafit.yus.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "MealBoardDTO", description = "식단게시물 정보")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealBoard {
	private int postNo;
	private String title;
	private String content;
	private String filepath;
	private int carb;
	private int protein;
	private int fat;
	private String userId;
	private int viewCnt;
	private String regDate;
}
