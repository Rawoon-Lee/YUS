package com.ssafit.yus.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "YoutubeCommDTO", description = "유튜브댓글 정보")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YoutubeComm {
	private int commIndex;
	private int classNo;
	private String comm;
	private int orderNo;
	private int commGroup;
	private String videoId;
	private String userId;
	private String regDate;
}
