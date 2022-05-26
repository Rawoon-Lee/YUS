package com.ssafit.yus.controller;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafit.yus.model.dao.MealBoardDao;
import com.ssafit.yus.model.dto.MealBoard;
import com.ssafit.yus.model.dto.MealBoardWithFile;
import com.ssafit.yus.model.dto.MealComm;
import com.ssafit.yus.model.dto.MealLiked;
import com.ssafit.yus.model.dto.RoutineLiked;
import com.ssafit.yus.model.service.MealBoardService;
import com.ssafit.yus.model.service.MealCommService;
import com.ssafit.yus.model.service.MealLikedService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "식단게시판 관련 api")
@RestController
@RequestMapping("/meal")
public class MealRestController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String PATH = new File("").getAbsolutePath();
	private static final String macOS = "/../front/vue-ssafit-yus/src/assets/MealBoard/";
	private static final String windowOS = "\\..\\front\\vue-ssafit-yus\\src\\assets\\MealBoard\\";
	@Autowired
	private MealBoardService mealBoardService;
	@Autowired
	private MealCommService mealCommService;
	@Autowired
	private MealLikedService mealLikedService;
	@Autowired
	private MealBoardDao mealBoardDao;
	
//===============================================식단 관련=============================================
	@ApiOperation(
			value = "식단 게시물 전체(리스트) 조회",
			notes = "LikedCnt도 함께 전달 됨"
	)
	@GetMapping("/info")
	public ResponseEntity<List<Map<String, String>>> infolist() {
		return new ResponseEntity<List<Map<String, String>>>(mealBoardService.selectAll(), HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "루틴 게시물 개별 조회",
			notes = "LikedCnt도 함께 전달 됨"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "postNo", value = "어떤 게시물을 조회할건지 전달", dataTypeClass = int.class, required = true)
	})
	@GetMapping("/info/{postNo}")
	public ResponseEntity<Map<String, String>> detail(@PathVariable int postNo){
		return new ResponseEntity<Map<String, String>>(mealBoardService.selectByPostNo(postNo), HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "루틴 게시물 조회수 증가",
			notes = "게시물 조회시 요청바람"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "postNo", value = "어떤 게시물을 조회할건지 전달", dataTypeClass = int.class, required = true)
	})
	@PutMapping("/info/view/{postNo}")
	public ResponseEntity<Map<String, String>> updateViewCnt(@PathVariable int postNo){
		Map<String, String> ret = new HashMap<String, String>();
		mealBoardService.updateViewCnt(postNo);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "식단 게시물 추가",
			notes = "별 다른 특이사항은 없고...title, content, filepath, carb, protein, fat, user_id만 전달 해주셈"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mealBoard", value = "추가 할 게시물의 속성들", dataTypeClass = MealBoard.class, required = true)
	})
	@PostMapping("/info")
	public ResponseEntity<Map<String, String>> addMeal(MealBoardWithFile mealBoard){
		Map<String, String> ret = new HashMap<String, String>();
		if (!mealBoard.getFile().isEmpty()) {
			String uuid = UUID.randomUUID() + "";
			File dest = new File(PATH + macOS + uuid + ".png");
            try {
            	mealBoard.getFile().transferTo(dest);
            } catch (IllegalStateException e) {
               e.printStackTrace();
            } catch (IOException e) {
               e.printStackTrace();
            }
            mealBoard.setFilepath(uuid);
		}
		mealBoardService.insertMealBoard(mealBoard);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.CREATED);
	}
	
	@ApiOperation(
			value = "식단 게시물 수정",
			notes = "title, content, filepath, carb, protein, fat 수정 가능"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mealBoard", value = "수정 할 게시물의 속성들", dataTypeClass = MealBoard.class, required = true)
	})
	@PutMapping("/info/modify")
	public ResponseEntity<Map<String, String>> modifyMeal(@RequestBody MealBoard mealBoard){
		Map<String, String> ret = new HashMap<String, String>();
		mealBoardService.updateMealBoard(0, mealBoard);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.CREATED);
	}
	@PutMapping("/info/modify/{postNo}")
	public ResponseEntity<Map<String, String>> modifyMealFile(@PathVariable int postNo, MultipartFile file){
		Map<String, String> ret = new HashMap<String, String>();
		MealBoard mealBoard = new MealBoard();
		mealBoard.setPostNo(postNo);
		if (!file.isEmpty()) {
			String uuid = mealBoardDao.selectByPostNo(postNo).getFilepath();
			if (uuid == null) uuid = UUID.randomUUID() + "";
			File dest = new File(PATH + macOS + uuid + ".png");
            try {
            	file.transferTo(dest);
            } catch (IllegalStateException e) {
               e.printStackTrace();
            } catch (IOException e) {
               e.printStackTrace();
            }
            mealBoard.setFilepath(uuid);
		}
		mealBoardService.updateMealBoard(1, mealBoard);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.CREATED);
	}
	
	@ApiOperation(
			value = "식단 게시물 삭제",
			notes = "작성자와 회원이 일치 할 때만 삭제 (백에서 검사 안함)"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "postNo", value = "삭제 할 게시물 번호", dataTypeClass = int.class, required = true)
	})
	@DeleteMapping("/info")
	public ResponseEntity<Map<String, String>> delMeal(@RequestBody int postNo){
		Map<String, String> ret = new HashMap<String, String>();
		mealBoardService.deleteMealBoard(postNo);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.OK);
	}
//===============================================코멘트 관련=============================================
	@ApiOperation(
			value = "식단 게시물의 댓글 조회",
			notes = "postNo로 식별(게시물 detail조회와 세트)"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "postNo", value = "어떤 식단 게시물의 댓글을 조회 할지 필요함", dataTypeClass = int.class, required = true)
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
		@ApiImplicitParam(name = "commIndex", value = "가져 올 댓글의 PK", dataTypeClass = int.class, required = true)
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
		@ApiImplicitParam(name = "mealComm", value = "postNo, userId, comm, classNo, commGroup", dataTypeClass = MealComm.class, required = true)
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
		@ApiImplicitParam(name = "commIndex", value = "PK", dataTypeClass = int.class, required = true)
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
		@ApiImplicitParam(name = "mealComm", value = "별 다른 정보가 필요하진 않고.. commIndex필요 할듯?", dataTypeClass = MealComm.class, required = true)
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
		@ApiImplicitParam(name = "mealComm", value = "바뀔 내용 comm과 commIndex 필요", dataTypeClass = MealComm.class, required = true)
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
		@ApiImplicitParam(name = "RoutineLiked", value = "userId와 postNo 필요", dataTypeClass = MealLiked.class, required = true)
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
		@ApiImplicitParam(name = "YoutubeLiked", value = "userId와 postNo 필요", dataTypeClass = MealLiked.class, required = true)
	})
	@PostMapping("/liked/add")
	public ResponseEntity<Map<String, String>> addLiked(@RequestBody MealLiked mealLiked){
		Map<String, String> ret = new HashMap<String, String>();
		System.out.println("========="+mealLiked.toString()+"==========");
		mealLikedService.insertMealLiked(mealLiked);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String,String>>(ret, HttpStatus.CREATED);
	}
	
	@ApiOperation(
			value = "좋아요 취소",
			notes = "좋아요 취소 버튼을 눌렸으면 해당 api에 요청 보내서 status를 false로 만들기 위한 용도(db도 비우고)"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "YoutubeLiked", value = "userId와 postNo 필요", dataTypeClass = MealLiked.class, required = true)
	})
	@PostMapping("/liked/del")
	public ResponseEntity<Map<String, String>> delLiked(@RequestBody MealLiked mealLiked){
		Map<String, String> ret = new HashMap<String, String>();
		mealLikedService.deleteByIds(mealLiked);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String,String>>(ret, HttpStatus.OK);
	}
}
