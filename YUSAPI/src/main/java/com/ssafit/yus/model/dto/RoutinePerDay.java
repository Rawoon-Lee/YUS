package com.ssafit.yus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutinePerDay {
	private int id;
	private int days;
	private int groupNo;
	private int routineNo;
}
