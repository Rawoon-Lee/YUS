package com.ssafit.yus.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "SuccessDTO", description = "사용자 별 루틴 성공 여부 정보")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Success {
	private int id;
	private String isDone;
	private String userId;
}
