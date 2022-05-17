package com.ssafit.yus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
