package com.ssafit.yus.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListForGroup {
	private GroupInfo groupInfo;
	private List<RoutinePerDay> rpdlist;
}
