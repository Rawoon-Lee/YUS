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

import com.ssafit.yus.model.dto.RoutineComm;
import com.ssafit.yus.model.dto.RoutineInfo;
import com.ssafit.yus.model.dto.RoutineLiked;
import com.ssafit.yus.model.dto.RoutinePerDay;
import com.ssafit.yus.model.service.RoutineCommService;
import com.ssafit.yus.model.service.RoutineInfoService;
import com.ssafit.yus.model.service.RoutineLikedService;
import com.ssafit.yus.model.service.RoutinePerDayService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/routine")
public class RoutineRestController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private RoutineCommService routineCommService;
	@Autowired
	private RoutineInfoService routineInfoService;
	@Autowired
	private RoutineLikedService routineLikedService;
	@Autowired
	private RoutinePerDayService routinePerDayService;
	
	@GetMapping("/info")
	public ResponseEntity<List<RoutineInfo>> infolist() {
		return new ResponseEntity<List<RoutineInfo>>(routineInfoService.getAll(), HttpStatus.OK);
	}
	@GetMapping("/liked")
	public ResponseEntity<List<RoutineLiked>> likedlist() {
		return new ResponseEntity<List<RoutineLiked>>(routineLikedService.getAll(), HttpStatus.OK);
	}
//===============================================코멘트 관련=============================================
	@ApiOperation(
			value = "루틴 게시물의 댓글 조회",
			notes = "routineNo로 식별(게시물 detail조회와 세트)"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "routineNo", value = "어떤 식단 게시물의 댓글을 조회 할지 필요함", required = true)
	})
	@GetMapping("/comm/{routineNo}")
	public ResponseEntity<List<RoutineComm>> commList(@PathVariable int routineNo) {
		return new ResponseEntity<List<RoutineComm>>(routineCommService.selectByRoutineNo(routineNo), HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "댓글 개별 조회",
			notes = "필요 없을거 같은데 혹시나 해서 만듬"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "commIndex", value = "가져 올 댓글의 PK", required = true)
	})
	@GetMapping("/comm/detail/{commIndex}")
	public ResponseEntity<RoutineComm> commDetail(@PathVariable int commIndex) {
		return new ResponseEntity<RoutineComm>(routineCommService.selectByCommindex(commIndex), HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "댓글 추가",
			notes = "부모 일 땐 class_no : 0, 자식이면 1, 부모의 commGroup은 백에서 처리함"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "routineComm", value = "routineNo, userId, comm, classNo, commGroup", required = true)
	})
	@PostMapping("/comm/add")
	public ResponseEntity<Map<String, String>> addComm(@RequestBody RoutineComm routineComm){
		Map<String, String> ret = new HashMap<String, String>();
		routineCommService.InsertRoutineComm(routineComm);
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
		routineCommService.deleteByCommIndex(commIndex);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "댓글 추가",
			notes = "삭제 된 척 수정으로 내용과 작성자 변경. 대댓글이 있거나, 대댓글인 경우의 댓글"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "routineComm", value = "별 다른 정보가 필요하진 않고.. commIndex필요 할듯?", required = true)
	})
	@PostMapping("/comm/delete")
	public ResponseEntity<Map<String, String>> deleteForm(@RequestBody RoutineComm routineComm){
		Map<String, String> ret = new HashMap<String, String>();
		routineCommService.updateForDelete(routineComm);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "댓글 수정",
			notes = "댓글의 내용(comm)을 수정"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "routineComm", value = "바뀔 내용 comm과 commIndex 필요", required = true)
	})
	@PostMapping("/comm/modify")
	public ResponseEntity<Map<String, String>> modifyComm(@RequestBody RoutineComm routineComm){
		Map<String, String> ret = new HashMap<String, String>();
		routineCommService.updateByCommIndex(routineComm);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.OK);
	}
	@GetMapping("/rpd")
	public ResponseEntity<List<RoutinePerDay>> rpdlist() {
		return new ResponseEntity<List<RoutinePerDay>>(routinePerDayService.getAll(), HttpStatus.OK);
	}
}
