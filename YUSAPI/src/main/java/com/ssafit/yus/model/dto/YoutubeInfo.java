package com.ssafit.yus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YoutubeInfo {
	private String videoId;
	private String url;
	private String title;
	private String channelName;
	private int exerciseNo;
	private int liked;
	private int viewCnt;
}
