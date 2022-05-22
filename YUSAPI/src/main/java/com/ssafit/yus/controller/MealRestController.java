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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ssafit.yus.model.dto.MealBoard;
import com.ssafit.yus.model.dto.MealComm;
import com.ssafit.yus.model.dto.MealLiked;
import com.ssafit.yus.model.dto.RoutineLiked;
import com.ssafit.yus.model.service.MealBoardService;
import com.ssafit.yus.model.service.MealCommService;
import com.ssafit.yus.model.service.MealLikedService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/meal")
public class MealRestController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private MealBoardService mealBoardService;
	@Autowired
	private MealCommService mealCommService;
	@Autowired
	private MealLikedService mealLikedService;
	
	@GetMapping("/info")
	public ResponseEntity<List<MealBoard>> infolist() {
		return new ResponseEntity<List<MealBoard>>(mealBoardService.getAll(), HttpStatus.OK);
	}
	
	
//===============================================코멘트 관련=============================================
	@ApiOperation(
			value = "식단 게시물의 댓글 조회",
			notes = "postNo로 식별(게시물 detail조회와 세트)"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "postNo", value = "어떤 식단 게시물의 댓글을 조회 할지 필요함", required = true)
	})
	@GetMapping("/comm/{postNo}")
	public ResponseEntity<List<MealComm>> commList(@PathVariable int postNo) {
		return new ResponseEntity<List<MealComm>>(mealCommService.selectByPostNo(postNo), HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "댓글 개별 조회",
			notes = "필요 없을거 같은데 혹시나 해서 만듬"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "commIndex", value = "가져 올 댓글의 PK", required = true)
	})
	@GetMapping("/comm/detail/{commIndex}")
	public ResponseEntity<MealComm> commDetail(@PathVariable int commIndex) {
		return new ResponseEntity<MealComm>(mealCommService.selectByCommindex(commIndex), HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "댓글 추가",
			notes = "부모 일 땐 class_no : 0, 자식이면 1, 부모의 commGroup은 백에서 처리함"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mealComm", value = "postNo, userId, comm, classNo, commGroup", required = true)
	})
	@PostMapping("/comm/add")
	public ResponseEntity<Map<String, String>> addComm(@RequestBody MealComm mealComm){
		Map<String, String> ret = new HashMap<String, String>();
		mealCommService.InsertMealComm(mealComm);
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
		mealCommService.deleteByCommIndex(commIndex);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "댓글 추가",
			notes = "삭제 된 척 수정으로 내용과 작성자 변경. 대댓글이 있거나, 대댓글인 경우의 댓글"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mealComm", value = "별 다른 정보가 필요하진 않고.. commIndex필요 할듯?", required = true)
	})
	@PostMapping("/comm/delete")
	public ResponseEntity<Map<String, String>> deleteForm(@RequestBody MealComm mealComm){
		Map<String, String> ret = new HashMap<String, String>();
		mealCommService.updateForDelete(mealComm);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "댓글 수정",
			notes = "댓글의 내용(comm)을 수정"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mealComm", value = "바뀔 내용 comm과 commIndex 필요", required = true)
	})
	@PostMapping("/comm/modify")
	public ResponseEntity<Map<String, String>> modifyComm(@RequestBody MealComm mealComm){
		Map<String, String> ret = new HashMap<String, String>();
		mealCommService.updateByCommIndex(mealComm);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.OK);
	}
	
	
//===============================================라이크 관련=============================================
	@ApiOperation(
			value = "사용자의 식단 게시물 좋아요 여부",
			notes = "front로 status를 전송 해줌. 눌렸으면 true, 아니면 false"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "RoutineLiked", value = "userId와 postNo 필요", required = true)
	})
	@PostMapping("/liked/check")
	public ResponseEntity<Map<String, String>> checkLiked(@RequestBody MealLiked mealLiked){
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("status", mealLikedService.checkStatus(mealLiked));
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String,String>>(ret, HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "좋아요 추가",
			notes = "좋아요 버튼을 눌렸으면 해당 api에 요청 보내서 status를 true로 만들기 위한 용도(db도 채우고)"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "YoutubeLiked", value = "userId와 postNo 필요", required = true)
	})
	@PostMapping("/liked/add")
	public ResponseEntity<Map<String, String>> addLiked(@RequestBody MealLiked mealLiked){
		Map<String, String> ret = new HashMap<String, String>();
		mealLikedService.insertMealLiked(mealLiked);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String,String>>(ret, HttpStatus.CREATED);
	}
	
	@ApiOperation(
			value = "좋아요 취소",
			notes = "좋아요 취소 버튼을 눌렸으면 해당 api에 요청 보내서 status를 false로 만들기 위한 용도(db도 비우고)"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "YoutubeLiked", value = "userId와 postNo 필요", required = true)
	})
	@PostMapping("/liked/del")
	public ResponseEntity<Map<String, String>> delLiked(@RequestBody MealLiked mealLiked){
		Map<String, String> ret = new HashMap<String, String>();
		mealLikedService.deleteByIds(mealLiked);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String,String>>(ret, HttpStatus.OK);
	}
}
