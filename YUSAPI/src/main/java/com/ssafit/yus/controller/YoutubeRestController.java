package com.ssafit.yus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	//===============================================유튜브 관련=============================================
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
	
	//===============================================코멘트 관련=============================================
	@ApiOperation(
			value = "유튜브 게시물의 댓글 조회",
			notes = "videoId로 식별(게시물 detail조회와 세트)"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "videoId", value = "어떤 video의 댓글을 조회 할지 필요함", required = true)
	})
	@GetMapping("/comm/{videoId}")
	public ResponseEntity<List<YoutubeComm>> commList(@PathVariable String videoId) {
		return new ResponseEntity<List<YoutubeComm>>(youtubeCommService.selectByVideoId(videoId), HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "댓글 개별 조회",
			notes = "필요 없을거 같은데 혹시나 해서 만듬"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "commIndex", value = "가져 올 댓글의 PK", required = true)
	})
	@GetMapping("/comm/detail/{commIndex}")
	public ResponseEntity<YoutubeComm> commList(@PathVariable int commIndex) {
		return new ResponseEntity<YoutubeComm>(youtubeCommService.selectByCommindex(commIndex), HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "댓글 추가",
			notes = "부모 일 땐 class_no : 0, 자식이면 1, 부모의 commGroup은 백에서 처리함"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "youtubeComm", value = "video_id,user_id,comm,class_no,comm_group", required = true)
	})
	@PostMapping("/comm/add")
	public ResponseEntity<Map<String, String>> addComm(@RequestBody YoutubeComm youtubeComm){
		Map<String, String> ret = new HashMap<String, String>();
		youtubeCommService.InsertYoutubeComm(youtubeComm);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.CREATED);
	}
	
	@ApiOperation(
			value = "댓글 삭제",
			notes = "PK로 댓글 삭제 (대댓글이 없는, 댓글만 해당)"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "commIndex", value = "PK", required = true)
	})
	@DeleteMapping("/comm/delete/{commIndex}")
	public ResponseEntity<Map<String, String>> deleteComm(@PathVariable int commIndex){
		Map<String, String> ret = new HashMap<String, String>();
		youtubeCommService.deleteByCommIndex(commIndex);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "댓글 추가",
			notes = "삭제 된 척 수정으로 내용과 작성자 변경. 대댓글이 있거나, 대댓글인 경우의 댓글"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "youtubeComm", value = "별 다른 정보가 필요하진 않고.. commIndex필요 할듯?", required = true)
	})
	@PostMapping("/comm/delete")
	public ResponseEntity<Map<String, String>> deleteForm(@RequestBody YoutubeComm youtubeComm){
		Map<String, String> ret = new HashMap<String, String>();
		youtubeCommService.updateForDelete(youtubeComm);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "댓글 수정",
			notes = "댓글의 내용(comm)을 수정"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "youtubeComm", value = "바뀔 내용 comm과 commIndex 필요", required = true)
	})
	@PostMapping("/comm/modify")
	public ResponseEntity<Map<String, String>> modifyComm(@RequestBody YoutubeComm youtubeComm){
		Map<String, String> ret = new HashMap<String, String>();
		youtubeCommService.updateByCommIndex(youtubeComm);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.OK);
	}
	
	//===============================================라이크 관련=============================================
	@ApiOperation(
			value = "게시물 리스트 별 좋아요 갯수 측정",
			notes = "게시물 리스트, 디테일을 불러올 때 필요함. 리스트를 렌더링 할려면 게시물 마다 요청 보내줘야 함"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "videoId", value = "좋아요 갯수를 측정 할 영상의 videoId", required = true)
	})
	@GetMapping("/liked/{videoId}")
	public ResponseEntity<Map<String, String>> cntLiked(@PathVariable String videoId){
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("count", "" + youtubeLikedService.countLikedByVideoId(videoId));
		return new ResponseEntity<Map<String,String>>(ret, HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "사용자의 영상 좋아요 여부",
			notes = "front로 status를 전송 해줌. 눌렸으면 true, 아니면 false"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "YoutubeLiked", value = "userId와 videoId 필요", required = true)
	})
	@PostMapping("/liked/check")
	public ResponseEntity<Map<String, String>> checkLiked(@RequestBody YoutubeLiked youtubeLiked){
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("status", youtubeLikedService.checkStatus(youtubeLiked));
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String,String>>(ret, HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "좋아요 추가",
			notes = "좋아요 버튼을 눌렸으면 해당 api에 요청 보내서 status를 true로 만들기 위한 용도(db도 채우고)"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "YoutubeLiked", value = "userId와 videoId 필요", required = true)
	})
	@PostMapping("/liked/add")
	public ResponseEntity<Map<String, String>> addLiked(@RequestBody YoutubeLiked youtubeLiked){
		Map<String, String> ret = new HashMap<String, String>();
		youtubeLikedService.insertYoutubeLiked(youtubeLiked);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String,String>>(ret, HttpStatus.CREATED);
	}
	
	@ApiOperation(
			value = "좋아요 취소",
			notes = "좋아요 취소 버튼을 눌렸으면 해당 api에 요청 보내서 status를 false로 만들기 위한 용도(db도 비우고)"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "YoutubeLiked", value = "userId와 videoId 필요", required = true)
	})
	@PostMapping("/liked/del")
	public ResponseEntity<Map<String, String>> delLiked(@RequestBody YoutubeLiked youtubeLiked){
		Map<String, String> ret = new HashMap<String, String>();
		youtubeLikedService.deleteByIds(youtubeLiked);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String,String>>(ret, HttpStatus.OK);
	}
}
