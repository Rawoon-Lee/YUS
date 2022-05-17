package com.ssafit.yus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private int userNo;
	private int liked;
	private int viewCnt;
}
