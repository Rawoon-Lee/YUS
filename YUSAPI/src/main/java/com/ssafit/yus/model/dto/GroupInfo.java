package com.ssafit.yus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupInfo {
	private int groupNo;
	private String groupName;
	private int exerciseType;
	private int groupPoint;
	private int maximumPeople;
	private int currentPeople;
}
