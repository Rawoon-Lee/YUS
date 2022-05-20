package com.ssafit.yus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.yus.model.dto.YoutubeComm;
import com.ssafit.yus.model.dto.YoutubeInfo;
import com.ssafit.yus.model.dto.YoutubeLiked;
import com.ssafit.yus.model.service.YoutubeCommService;
import com.ssafit.yus.model.service.YoutubeInfoService;
import com.ssafit.yus.model.service.YoutubeLikedService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Api(tags = "유튜브 관련 api")
@RestController
@RequestMapping("/youtube")
public class YoutubeRestController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	@Autowired
	private YoutubeCommService youtubeCommService;
	@Autowired
	private YoutubeInfoService youtubeInfoService;
	@Autowired
	private YoutubeLikedService youtubeLikedService;
	
	//유튜브 관련
	@ApiOperation(
			value = "유튜브 게시물 전체 조회",
			notes = "나중에 페이지네이션 할때 수정해야할듯"
	)
	@GetMapping("/info")
	public ResponseEntity<List<YoutubeInfo>> infoList() {
		return new ResponseEntity<List<YoutubeInfo>>(youtubeInfoService.getAll(), HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "유튜브 게시물 상세 조회",
			notes = "videoId로 식별"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "videoId", value = "디테일로 들어갈 때 필요한 videoId", required = true)
	})
	@GetMapping("/info/{videoId}")
	public ResponseEntity<YoutubeInfo> infoDetail(@PathVariable String videoId) {
		return new ResponseEntity<YoutubeInfo>(youtubeInfoService.selectById(videoId), HttpStatus.OK);
	}
	
	
	//코멘트 관련
	@GetMapping("/comm")
	public ResponseEntity<List<YoutubeComm>> commList() {
		return new ResponseEntity<List<YoutubeComm>>(youtubeCommService.getAll(), HttpStatus.OK);
	}
	
	
	//라이크 관련
	@GetMapping("/liked/{videoId}")
	public ResponseEntity<Map<String, String>> cntLiked(@PathVariable String videoId){
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("count", "" + youtubeLikedService.countLikedByVideoId(videoId));
		return new ResponseEntity<Map<String,String>>(ret, HttpStatus.OK);
	}
	
	@PostMapping("/liked/check")
	public ResponseEntity<Map<String, String>> checkLiked(@RequestBody YoutubeLiked youtubeLiked){
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("status", youtubeLikedService.checkStatus(youtubeLiked));
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String,String>>(ret, HttpStatus.OK);
	}
	
	@PostMapping("/liked/insert")
	public ResponseEntity<Map<String, String>> isnertLiked(@RequestBody YoutubeLiked youtubeLiked){
		Map<String, String> ret = new HashMap<String, String>();
		youtubeLikedService.insertYoutubeLiked(youtubeLiked);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String,String>>(ret, HttpStatus.CREATED);
	}
	
	@PostMapping("/liked/delete")
	public ResponseEntity<Map<String, String>> deleteLiked(@RequestBody YoutubeLiked youtubeLiked){
		Map<String, String> ret = new HashMap<String, String>();
		youtubeLikedService.deleteByIds(youtubeLiked);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String,String>>(ret, HttpStatus.OK);
	}
}
