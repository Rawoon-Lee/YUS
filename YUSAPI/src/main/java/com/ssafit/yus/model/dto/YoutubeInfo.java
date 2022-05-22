package com.ssafit.yus.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "YoutubeInfoDTO", description = "유튜브 정보")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YoutubeInfo {
	private String videoId;
	private String url;
	private String title;
	private String channelName;
	private String exerciseName;
	private int viewCnt;
}
