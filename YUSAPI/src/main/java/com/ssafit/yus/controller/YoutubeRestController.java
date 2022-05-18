package com.ssafit.yus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.yus.model.dto.YoutubeComm;
import com.ssafit.yus.model.dto.YoutubeInfo;
import com.ssafit.yus.model.dto.YoutubeLiked;
import com.ssafit.yus.model.service.YoutubeCommService;
import com.ssafit.yus.model.service.YoutubeInfoService;
import com.ssafit.yus.model.service.YoutubeLikedService;

@RestController
@RequestMapping("/youtube")
public class YoutubeRestController {
	@Autowired
	private YoutubeCommService youtubeCommService;
	@Autowired
	private YoutubeInfoService youtubeInfoervice;
	@Autowired
	private YoutubeLikedService youtubeLikedervice;
	
	@GetMapping("/info")
	public ResponseEntity<List<YoutubeInfo>> infoList() {
		return new ResponseEntity<List<YoutubeInfo>>(youtubeInfoervice.getAll(), HttpStatus.OK);
	}
	@GetMapping("/comm")
	public ResponseEntity<List<YoutubeComm>> commList() {
		return new ResponseEntity<List<YoutubeComm>>(youtubeCommService.getAll(), HttpStatus.OK);
	}
	@GetMapping("/liked")
	public ResponseEntity<List<YoutubeLiked>> likedList() {
		return new ResponseEntity<List<YoutubeLiked>>(youtubeLikedervice.getAll(), HttpStatus.OK);
	}
}
