package com.ssafit.yus.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "GroupInfoDTO", description = "챌린지그룹 정보")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupInfo {
	private int groupNo;
	private String groupName;
	private int groupPoint;
	private int maximumPeople;
	private int currentPeople;
	private String userId;
}
