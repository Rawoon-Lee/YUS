package com.ssafit.yus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
