package com.ssafit.yus.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.yus.model.dto.ExercisePerRoutine;
import com.ssafit.yus.model.dto.ListForRoutine;
import com.ssafit.yus.model.dto.RoutineComm;
import com.ssafit.yus.model.dto.RoutineInfo;
import com.ssafit.yus.model.dto.RoutineLiked;
import com.ssafit.yus.model.dto.RoutinePerDay;
import com.ssafit.yus.model.dto.UserInfo;
import com.ssafit.yus.model.dto.YoutubeLiked;
import com.ssafit.yus.model.service.RoutineCommService;
import com.ssafit.yus.model.service.RoutineInfoService;
import com.ssafit.yus.model.service.RoutineLikedService;
import com.ssafit.yus.model.service.RoutinePerDayService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "루틴 관련 api")
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
	
//===============================================루틴 관련=============================================
	@ApiOperation(
			value = "루틴 게시물 전체(목록) 조회",
			notes = "LikedCnt도 함께 전달 됨"
	)
	@GetMapping("/info")
	public ResponseEntity<List<Map<String, String>>> infolist() {
		return new ResponseEntity<List<Map<String, String>>>(routineInfoService.selectAll(), HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "루틴 게시물 개별 조회",
			notes = "LikedCnt도 함께 전달 됨"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "routineNo", value = "어떤 게시물을 조회할건지 전달", dataTypeClass = int.class, required = true)
	})
	@GetMapping("/info/{routineNo}")
	public ResponseEntity<List<Object> > detail(@PathVariable int routineNo){
		return new ResponseEntity<List<Object> >(routineInfoService.selectByRoutineNo(routineNo), HttpStatus.OK);
	}
	
	
	@ApiOperation(
			value = "루틴 게시물 + 루틴별운동 추가",
			notes = "제이슨 배열의 제일 첫번째 객체엔 RoutineInfo가 올 것, 이후론 ExercisePerRoutine규격의 객체 전달"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "list", value = "RoutineInfo + EPR 객체 배열", dataTypeClass = List.class, required = true)
	})
	@PostMapping("/info")
	public ResponseEntity<Map<String, String>> addRoutine(@RequestBody ListForRoutine lfr){
		Map<String, String> ret = new HashMap<String, String>();
		System.out.println(lfr.toString());
		routineInfoService.insertRoutineInfo(lfr);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "루틴 게시물 조회수 증가",
			notes = "디테일로 들어가기 전 요청 바람"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "routineNo", value = "어떤 게시물인지 전달", dataTypeClass = int.class, required = true)
	})
	
	@PutMapping("/info/view")
	public ResponseEntity<Map<String, String>> updateViewCnt(@RequestBody RoutineInfo routineInfo){
		Map<String, String> ret = new HashMap<String, String>();
		routineInfoService.updateViewCnt(routineInfo.getRoutineNo());
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.OK);
	}
	
//===============================================코멘트 관련=============================================
	@ApiOperation(
			value = "루틴 게시물의 댓글 조회",
			notes = "routineNo로 식별(게시물 detail조회와 세트)"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "routineNo", value = "어떤 루틴 게시물의 댓글을 조회 할지 필요함", dataTypeClass = int.class, required = true)
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
		@ApiImplicitParam(name = "commIndex", value = "가져 올 댓글의 PK", dataTypeClass = int.class, required = true)
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
		@ApiImplicitParam(name = "routineComm", value = "routineNo, userId, comm, classNo, commGroup", dataTypeClass = RoutineComm.class, required = true)
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
		@ApiImplicitParam(name = "commIndex", value = "PK", dataTypeClass = int.class, required = true)
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
		@ApiImplicitParam(name = "routineComm", value = "별 다른 정보가 필요하진 않고.. commIndex필요 할듯?", dataTypeClass = RoutineComm.class, required = true)
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
		@ApiImplicitParam(name = "routineComm", value = "바뀔 내용 comm과 commIndex 필요", dataTypeClass = RoutineComm.class, required = true)
	})
	@PostMapping("/comm/modify")
	public ResponseEntity<Map<String, String>> modifyComm(@RequestBody RoutineComm routineComm){
		Map<String, String> ret = new HashMap<String, String>();
		routineCommService.updateByCommIndex(routineComm);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.OK);
	}
//===============================================라이크 관련=============================================
	@ApiOperation(
			value = "사용자의 루틴 좋아요 여부",
			notes = "front로 status를 전송 해줌. 눌렸으면 true, 아니면 false"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "RoutineLiked", value = "userId와 routineNo 필요", dataTypeClass = RoutineLiked.class, required = true)
	})
	@PostMapping("/liked/check")
	public ResponseEntity<Map<String, String>> checkLiked(@RequestBody RoutineLiked routineLiked){
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("status", routineLikedService.checkStatus(routineLiked));
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String,String>>(ret, HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "좋아요 추가",
			notes = "좋아요 버튼을 눌렸으면 해당 api에 요청 보내서 status를 true로 만들기 위한 용도(db도 채우고)"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "routineLiked", value = "userId와 routineNo 필요", dataTypeClass = RoutineLiked.class, required = true)
	})
	@PostMapping("/liked/add")
	public ResponseEntity<Map<String, String>> addLiked(@RequestBody RoutineLiked routineLiked){
		Map<String, String> ret = new HashMap<String, String>();
		routineLikedService.insertRoutineLiked(routineLiked);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String,String>>(ret, HttpStatus.CREATED);
	}
	
	@ApiOperation(
			value = "좋아요 취소",
			notes = "좋아요 취소 버튼을 눌렸으면 해당 api에 요청 보내서 status를 false로 만들기 위한 용도(db도 비우고)"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "routineLiked", value = "userId와 routineNo 필요", dataTypeClass = RoutineLiked.class, required = true)
	})
	@PostMapping("/liked/del")
	public ResponseEntity<Map<String, String>> delLiked(@RequestBody RoutineLiked routineLiked){
		Map<String, String> ret = new HashMap<String, String>();
		routineLikedService.deleteByIds(routineLiked);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String,String>>(ret, HttpStatus.OK);
	}
}
