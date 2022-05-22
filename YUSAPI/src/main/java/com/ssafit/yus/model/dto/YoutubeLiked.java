package com.ssafit.yus.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "YoutubeLikedDTO", description = "유튜브 좋아요 정보")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YoutubeLiked {
	private int id;
	private String userId;
	private String videoId;
}
